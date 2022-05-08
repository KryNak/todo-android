package pl.edu.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import pl.edu.todo.Navigable
import pl.edu.todo.R
import pl.edu.todo.adapters.TodosAdapter
import pl.edu.todo.data.DataSource
import pl.edu.todo.data.TaskCounter
import pl.edu.todo.databinding.FragmentListBinding
import pl.edu.todo.enums.FormType
import pl.edu.todo.enums.NavigationOptions
import pl.edu.todo.utils.TodoDeadlineChecker

class ListFragment : Fragment(), Navigable {

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        TodosAdapter.adapter = TodosAdapter(requireActivity())

        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TodosAdapter.adapter.replace(DataSource.todos)

        val counter = TodoDeadlineChecker.check(DataSource.todos)
        TaskCounter.counter.postValue(counter)

        TaskCounter.counter.observe(this.viewLifecycleOwner) {
            binding.counter.text = it.toString()
        }

        binding.todos.let {
            it.adapter = TodosAdapter.adapter
            it.layoutManager = LinearLayoutManager(requireContext())
        }

        binding.btnAdd.setOnClickListener {
            navigate(NavigationOptions.FORM_FRAGMENT)
        }
    }

    override fun navigate(to: NavigationOptions) {
        when(to) {
            NavigationOptions.FORM_FRAGMENT -> {
                val fragment = FormFragment(FormType.ADD_FORM)

                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, fragment, fragment.javaClass.name)
                    .addToBackStack(fragment.javaClass.name)
                    .commit()
            }
            else -> {
                throw UnsupportedOperationException()
            }
        }
    }
}