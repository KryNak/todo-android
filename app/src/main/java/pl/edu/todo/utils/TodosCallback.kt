package pl.edu.todo.utils

import androidx.recyclerview.widget.DiffUtil
import pl.edu.todo.model.Todo

class TodosCallback(
    private val newData: List<Todo>,
    private val oldData: List<Todo>
): DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldData.size
    }

    override fun getNewListSize(): Int {
        return newData.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] === newData[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] == newData[newItemPosition]
    }
}