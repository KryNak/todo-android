package pl.edu.todo

import pl.edu.todo.enums.NavigationOptions
import pl.edu.todo.model.Todo

interface Navigable {

    fun navigate(to: NavigationOptions, todo: Todo? = null)

}