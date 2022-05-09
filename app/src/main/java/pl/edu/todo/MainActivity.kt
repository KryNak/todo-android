package pl.edu.todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.edu.todo.databinding.ActivityMainBinding
import pl.edu.todo.enums.FormType
import pl.edu.todo.enums.NavigationOptions
import pl.edu.todo.fragments.FormFragment
import pl.edu.todo.fragments.ListFragment
import pl.edu.todo.model.Todo

class MainActivity : AppCompatActivity(), Navigable {

    private var listFragment: ListFragment = ListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, listFragment, listFragment.javaClass.name)
            .commit()
    }

    override fun navigate(to: NavigationOptions, todo: Todo?) {
        when(to) {
            NavigationOptions.LIST_FRAGMENT -> {

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, listFragment, listFragment.javaClass.name)
                    .commit()
            }
            NavigationOptions.ADD_FRAGMENT -> {
                val fragment = FormFragment(FormType.ADD_FORM)

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, fragment, fragment.javaClass.name)
                    .addToBackStack(listFragment.javaClass.name)
                    .commit()
            }
            NavigationOptions.EDIT_FRAGMENT -> {
                val fragment = FormFragment(FormType.EDIT_FORM, todo)

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, fragment, fragment.javaClass.name)
                    .addToBackStack(listFragment.javaClass.name)
                    .commit()
            }

        }
    }


}