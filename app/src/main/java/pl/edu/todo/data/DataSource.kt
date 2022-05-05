package pl.edu.todo.data

import pl.edu.todo.enums.Priority
import pl.edu.todo.model.Todo
import java.time.LocalDateTime

object DataSource {

    val todos: MutableList<Todo> = mutableListOf()

    init {
        todos.add(Todo("task1", Priority.LOW, 45, LocalDateTime.now().plusDays(1)))
        todos.add(Todo("task2", Priority.HIGH, 45, LocalDateTime.now().minusDays(1)))
        todos.add(Todo("task3", Priority.LOW, 45, LocalDateTime.now().plusMinutes(5)))
        todos.add(Todo("task4", Priority.LOW, 45, LocalDateTime.now()))
        todos.add(Todo("task5", Priority.MEDIUM, 45, LocalDateTime.now()))
        todos.add(Todo("task6", Priority.MEDIUM, 55, LocalDateTime.now()))
        todos.add(Todo("ask", Priority.MEDIUM, 55, LocalDateTime.now()))
        todos.add(Todo("task7", Priority.HIGH, 45, LocalDateTime.now()))
        todos.add(Todo("task8", Priority.HIGH, 45, LocalDateTime.now().minusMinutes(2)))
        todos.add(Todo("task9", Priority.MEDIUM, 45, LocalDateTime.now().plusDays(7)))
    }

}