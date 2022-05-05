package pl.edu.todo.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

object TaskCounter: ViewModel(){

    val counter: MutableLiveData<Int> = MutableLiveData(0)

}