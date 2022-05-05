package pl.edu.todo.utils

import pl.edu.todo.data.DataSource
import pl.edu.todo.model.Todo
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime

class TodoDeadlineChecker {
    companion object {
        fun check(todos: List<Todo>): Int {
            return DataSource.todos.filter {
                var lastDayOfWeek = LocalDate.now()
                while (lastDayOfWeek.dayOfWeek != DayOfWeek.SUNDAY) {
                    lastDayOfWeek = lastDayOfWeek.plusDays(1)
                }

                it.deadline.isAfter(LocalDateTime.now())
                        && it.deadline.isBefore(lastDayOfWeek.atTime(23, 59))
            }.size
        }
    }
}