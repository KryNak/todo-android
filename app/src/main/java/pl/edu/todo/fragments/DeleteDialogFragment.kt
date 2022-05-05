package pl.edu.todo.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import pl.edu.todo.adapters.TodosAdapter
import pl.edu.todo.data.DataSource
import pl.edu.todo.data.TaskCounter
import pl.edu.todo.model.Todo
import pl.edu.todo.utils.TodoDeadlineChecker

class DeleteDialogFragment(
    private val todo: Todo,
    private val todosAdapter: TodosAdapter
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return requireActivity().let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Do you want to delete this task?")
                .setPositiveButton("Yes") { dialog, id ->
                    DataSource.todos.remove(todo)

                    val counter: Int = TodoDeadlineChecker.check(DataSource.todos)
                    TaskCounter.counter.postValue(counter)

                    todosAdapter.replace(DataSource.todos)
                }
                .setNegativeButton("No") { _, _ -> }
            builder.create()
        }
    }

}