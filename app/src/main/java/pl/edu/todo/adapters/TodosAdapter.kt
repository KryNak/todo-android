package pl.edu.todo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pl.edu.todo.fragments.PreviewFragment
import pl.edu.todo.databinding.ListItemBinding
import pl.edu.todo.fragments.DeleteDialogFragment
import pl.edu.todo.model.Todo
import pl.edu.todo.utils.TodosCallback
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TodoClassHolder(
    private val binding: ListItemBinding,
    private val activity: FragmentActivity
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(todo: Todo) {
        binding.apply {
            for (field in listOf(taskNameField, progressField, priorityField, deadlineField)) {
                field.setOnLongClickListener {
                    cardComponent.performLongClick()
                    true
                }

                field.setOnClickListener {
                    cardComponent.performClick()
                }
            }
        }

        val taskName = todo.taskName
        val priority = todo.priority.name.lowercase().replaceFirstChar(Char::uppercase)
        val progress = "${todo.progress}%"

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val deadline = formatter.format(todo.deadline)

        binding.taskNameField.setText(taskName)
        binding.priorityField.setText(priority)
        binding.progressField.setText(progress)
        binding.deadlineField.setText(deadline)

        binding.cardComponent.setOnLongClickListener {
            DeleteDialogFragment(todo, TodosAdapter.adapter).show(
                activity.supportFragmentManager,
                DeleteDialogFragment::class.java.name
            )
            true
        }

        binding.cardComponent.setOnClickListener {
            PreviewFragment(todo).show(
                activity.supportFragmentManager,
                PreviewFragment::class.java.name
            )
        }
    }


}

class TodosAdapter(
    private val activity: FragmentActivity
) : RecyclerView.Adapter<TodoClassHolder>() {

    private val data: MutableList<Todo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoClassHolder {
        val binding: ListItemBinding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TodoClassHolder(binding, activity)
    }

    override fun onBindViewHolder(holder: TodoClassHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun replace(newData: List<Todo>) {
        val oldData = data.toList()
        val newDataCopy = newData.filter {
            it.deadline.isAfter(LocalDateTime.now())
        }.sorted()

        val callback = TodosCallback(oldData = oldData, newData = newDataCopy)

        data.clear()
        data.addAll(newDataCopy)

        val diff: DiffUtil.DiffResult = DiffUtil.calculateDiff(callback)
        diff.dispatchUpdatesTo(this)
    }

    companion object {
        lateinit var adapter: TodosAdapter
    }
}

