package pl.edu.todo

import pl.edu.todo.enums.NavigationOptions

interface Navigable {

    fun navigate(to: NavigationOptions)

}