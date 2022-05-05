package pl.edu.todo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import pl.edu.todo.enums.NavigationOptions
import pl.edu.todo.enums.TransactionOperation
import pl.edu.todo.fragments.AddFragment
import pl.edu.todo.fragments.ListFragment

interface Navigable {

    fun navigate(to: NavigationOptions) {
        val fragment: Fragment = createFragment(to)
        val fragmentClassName: String = fragment.javaClass.name

        val activity: FragmentActivity = provideActivityContext()
        activity.supportFragmentManager
            .beginTransaction()
            .let {
                when(chooseOperation()) {
                    TransactionOperation.ADD -> {
                        it.add(R.id.container, fragment, fragmentClassName)
                    }
                    TransactionOperation.REPLACE -> {
                        it.replace(R.id.container, fragment, fragmentClassName)
                    }
                }
            }
            .also { if(shouldAddToBackStack()) it.addToBackStack(fragmentClassName) }
            .commit()

    }

    fun provideActivityContext(): FragmentActivity
    fun chooseOperation(): TransactionOperation
    fun shouldAddToBackStack(): Boolean = true

    private fun createFragment(option: NavigationOptions): Fragment{
        return when (option) {
            NavigationOptions.ADD_FRAGMENT -> {
                AddFragment()
            }
            NavigationOptions.EDIT_FRAGMENT -> {
                throw NotImplementedError()
            }
            NavigationOptions.LIST_FRAGMENT -> {
                ListFragment()
            }
        }
    }

}