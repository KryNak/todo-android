package pl.edu.todo.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import pl.edu.todo.MainActivity
import pl.edu.todo.data.DataSource
import pl.edu.todo.databinding.FragmentAddBinding
import pl.edu.todo.enums.FormType
import pl.edu.todo.enums.NavigationOptions
import pl.edu.todo.enums.Priority
import pl.edu.todo.listeners.OnAddProgressChangeListener
import pl.edu.todo.model.Todo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class FormFragment(
    private val formType: FormType,
    private val todo: Todo? = null
) : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return FragmentAddBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(formType == FormType.EDIT_FORM){

            binding.taskDesc.setText(todo!!.taskName)
            binding.dateField.setText(formatter.format(todo.deadline))
            binding.proritiesList.setSelection(todo.priority.order % 2)
            binding.progressBar.progress = todo.progress
            binding.progressText.text = todo.progress.toString()

            binding.btn.text = "Edit"

        }

        binding.dateField.setOnClickListener {
            this.requireActivity().currentFocus?.let {
                val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                imm!!.hideSoftInputFromWindow(view.windowToken, 0)
            }

            binding.taskDesc.clearFocus()

            DatePickerDialog(binding.dateField).show(
                requireActivity().supportFragmentManager,
                DatePickerDialog::class.java.name
            )
        }

        binding.progressBar.setOnSeekBarChangeListener(OnAddProgressChangeListener(binding.progressText))
        binding.btn.setOnClickListener {
            if(isFormValid(binding)){

                val taskName = binding.taskDesc.text.toString()

                val priority = Priority.valueOf(binding.proritiesList.selectedItem.toString().uppercase())

                val progress = binding.progressBar.progress

                val date = LocalDateTime.parse(binding.dateField.text.toString(), formatter)


                saveInDatasource(Todo(taskName, priority, progress, date))
                (requireActivity() as? MainActivity)?.navigate(NavigationOptions.LIST_FRAGMENT)
            }
            else {
                Toast.makeText(
                    requireContext(),
                    "All text fields should be filled!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun isFormValid(binding: FragmentAddBinding): Boolean {
        return binding.dateField.text.isNotBlank()
                && binding.taskDesc.text.isNotBlank()
    }


    private fun saveInDatasource(newTodo: Todo) {
        when(formType) {
            FormType.EDIT_FORM -> {
                val index = DataSource.todos.indexOf(todo)
                DataSource.todos[index] = newTodo
            }
            FormType.ADD_FORM -> {
                DataSource.todos.add(newTodo)
            }
        }
    }

}