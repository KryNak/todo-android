package pl.edu.todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.edu.todo.databinding.ActivityMainBinding
import pl.edu.todo.enums.NavigationOptions
import pl.edu.todo.fragments.ListFragment

class MainActivity : AppCompatActivity(), Navigable {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigate(NavigationOptions.LIST_FRAGMENT)
    }

    override fun navigate(to: NavigationOptions) {
        when(to) {
            NavigationOptions.LIST_FRAGMENT -> {
                val fragment = ListFragment()

                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, fragment, fragment.javaClass.name)
                    .commit()
            }
            else -> {
                throw UnsupportedOperationException()
            }
        }
    }


}