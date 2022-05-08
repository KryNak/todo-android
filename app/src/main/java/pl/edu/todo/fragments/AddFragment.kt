package pl.edu.todo.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import pl.edu.todo.Navigable
import pl.edu.todo.data.DataSource
import pl.edu.todo.databinding.FragmentAddBinding
import pl.edu.todo.enums.NavigationOptions
import pl.edu.todo.enums.Priority
import pl.edu.todo.enums.TransactionOperation
import pl.edu.todo.listeners.OnAddProgressChangeListener
import pl.edu.todo.model.Todo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class AddFragment : Fragment(), Navigable {

    private lateinit var binding: FragmentAddBinding

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

        binding.dateField.setOnClickListener {
            val view: View? = this.requireActivity().currentFocus
            if (view != null) {
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
        binding.addBtn.setOnClickListener {
            if(isFormValid(binding)){

                val taskName = binding.taskDesc.text.toString()

                val priority = Priority.valueOf(binding.proritiesList.selectedItem.toString().uppercase())

                val progress = binding.progressBar.progress

                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                val date = LocalDateTime.parse(binding.dateField.text.toString(), formatter)

                DataSource.todos.add(Todo(taskName, priority, progress, date))
                navigate(NavigationOptions.LIST_FRAGMENT)
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

    override fun provideActivityContext(): FragmentActivity {
        return requireActivity()
    }

    override fun chooseOperation(): TransactionOperation {
        return TransactionOperation.REPLACE
    }

    private fun isFormValid(binding: FragmentAddBinding): Boolean {
        return binding.dateField.text.isNotBlank()
                && binding.taskDesc.text.isNotBlank()
    }

}