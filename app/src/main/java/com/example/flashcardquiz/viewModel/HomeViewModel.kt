package com.example.flashcardquiz.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.flashcardquiz.data.QuizDataBase
import com.example.flashcardquiz.data.dao.QuizDao
import com.example.flashcardquiz.data.model.QuizEntity
import kotlinx.coroutines.launch

class HomeViewModel(dao: QuizDao): ViewModel(){
    val quizAll = dao.getAllQuiz()
    fun getBalance(list: List<QuizEntity>): String{
        var total = 0.0
        list.forEach{ _ ->
            total += 1
            println(total)

        }
        return "$total"
    }
}

class HomeViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val dao = QuizDataBase.getDatabase(context).quizDao()
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown Error")
    }
}