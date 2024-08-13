package com.example.flashcardquiz.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.flashcardquiz.data.QuizDataBase
import com.example.flashcardquiz.data.dao.QuizDao
import com.example.flashcardquiz.data.model.QuizEntity
import kotlinx.coroutines.launch

class QuizViewModel(dao: QuizDao , category: String): ViewModel(){
    val quizAll = dao.getAllQuiz()
    val getQuizByCategory = dao.getQuizByCategory(category)


}


class QuizViewModelFactory(private val context: Context, private val category: String): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)){
            val dao = QuizDataBase.getDatabase(context).quizDao()
            val category = category
            @Suppress("UNCHECKED_CAST")
            return QuizViewModel(dao, category = category) as T
        }
        throw IllegalArgumentException("Unknown Error")
    }
}
