package pl.edu.todo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import pl.edu.todo.databinding.ActivityMainBinding
import pl.edu.todo.enums.NavigationOptions
import pl.edu.todo.enums.TransactionOperation

class MainActivity : AppCompatActivity(), Navigable {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigate(NavigationOptions.LIST_FRAGMENT)
    }

    override fun provideActivityContext(): FragmentActivity {
        return this
    }

    override fun chooseOperation(): TransactionOperation {
        return TransactionOperation.ADD
    }

    override fun shouldAddToBackStack(): Boolean {
        return false
    }
}