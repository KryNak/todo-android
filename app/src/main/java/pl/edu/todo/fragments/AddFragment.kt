package pl.edu.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import pl.edu.todo.Navigable
import pl.edu.todo.data.DataSource
import pl.edu.todo.databinding.FragmentAddBinding
import pl.edu.todo.enums.NavigationOptions
import pl.edu.todo.enums.Priority
import pl.edu.todo.enums.TransactionOperation
import pl.edu.todo.listeners.OnSeekChangeListener
import pl.edu.todo.model.Todo
import java.time.LocalDateTime

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

        binding.datePickerBtn.setOnClickListener {
            DatePickerFragment(binding.dateField).show(
                requireActivity().supportFragmentManager,
                DatePickerFragment::class.java.name
            )
        }

        binding.progressBar.setOnSeekBarChangeListener(OnSeekChangeListener(binding.progressText))
        binding.addBtn.setOnClickListener {
            DataSource.todos.add(
                Todo(
                    taskName = binding.taskDesc.text.toString(),
                    progress = binding.progressBar.progress,
                    priority = Priority.valueOf(binding.proritiesList.selectedItem.toString().uppercase()),
                    deadline = LocalDateTime.parse(binding.dateField.text.toString())
                )
            )

            navigate(NavigationOptions.LIST_FRAGMENT)
        }
    }

    override fun provideActivityContext(): FragmentActivity {
        return requireActivity()
    }

    override fun chooseOperation(): TransactionOperation {
        return TransactionOperation.REPLACE
    }

}