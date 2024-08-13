package com.example.flashcardquiz.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel() : ViewModel() {
    private val _repository: CounterRepository = CounterRepository()
    private val _count = mutableStateOf(_repository.getCounter().count)

    // Expose count as immutable state
    val  count: MutableState<Int> = _count

    fun increment(){
        _repository.increment()
        _count.value=_repository.getCounter().count
    }
    fun decrement(){
        _repository.decrement()
        _count.value = _repository.getCounter().count
    }
}


data class CounterModel(
    var count :Int
)

class CounterRepository(){
    private val _counter = CounterModel(0)

    fun getCounter() = _counter

    fun increment(){
        _counter.count++
    }
    fun decrement(){
        _counter.count--
    }
}