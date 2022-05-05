package pl.edu.todo.model

import pl.edu.todo.enums.Priority
import java.time.LocalDateTime

data class Todo(
    val taskName: String,
    val priority: Priority,
    val progress: Int,
    val deadline: LocalDateTime
): Comparable<Todo> {

    override fun compareTo(other: Todo): Int {
        var comparison = deadline.compareTo(other.deadline)

        if(comparison == 0){
            comparison = priority.order.compareTo(other.priority.order)
        }

        if(comparison == 0){
            comparison = progress.compareTo(other.progress) * -1
        }

        if(comparison == 0){
            comparison = taskName.compareTo(other.taskName, true)
        }

        return comparison
    }

}
