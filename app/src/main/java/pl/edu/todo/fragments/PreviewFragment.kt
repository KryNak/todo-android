package pl.edu.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import pl.edu.todo.adapters.TodosAdapter
import pl.edu.todo.data.DataSource
import pl.edu.todo.databinding.FragmentPreviewBinding
import pl.edu.todo.listeners.OnPreviewProgressChangeListener
import pl.edu.todo.model.Todo
import java.time.format.DateTimeFormatter


class PreviewFragment(
    val todo: Todo
) : DialogFragment() {

    lateinit var binding: FragmentPreviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val priority = todo.priority.toString().lowercase().replaceFirstChar(Char::uppercase)

        binding.taskNameField.setText(todo.taskName)
        binding.priorityField.setText(priority)
        binding.deadlineField.setText(dateFormatter.format(todo.deadline))
        binding.progressField.progress = todo.progress
        binding.statsProgressbar.progress = todo.progress
        binding.percentageProgressLabel.text = todo.progress.toString()

        binding.progressField.setOnSeekBarChangeListener(
            OnPreviewProgressChangeListener(binding.statsProgressbar, binding.percentageProgressLabel)
        )

        binding.saveBtn.setOnClickListener {
            val index = DataSource.todos.indexOf(todo)
            DataSource.todos[index] = Todo(
                todo.taskName,
                todo.priority,
                binding.statsProgressbar.progress,
                todo.deadline
            )

            TodosAdapter.adapter.replace(DataSource.todos)
            dismiss()
        }

    }

}