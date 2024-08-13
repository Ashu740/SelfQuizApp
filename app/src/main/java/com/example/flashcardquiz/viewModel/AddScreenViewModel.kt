package com.example.flashcardquiz.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flashcardquiz.data.QuizDataBase
import com.example.flashcardquiz.data.dao.QuizDao
import com.example.flashcardquiz.data.model.QuizEntity

class AddScreenViewModel(val dao: QuizDao) : ViewModel(){
    suspend fun addQuiz(quizEntity: QuizEntity): Boolean{
        return try {
            dao.insertQuiz(quizEntity)
            true
        }catch (e: Throwable){
            e.printStackTrace()
            false
        }
    }
}

class  AddScreenViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddScreenViewModel::class.java)){
            val dao = QuizDataBase.getDatabase(context).quizDao()
            @Suppress("UNCHECKED_CAST")
            return AddScreenViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown Error")
    }
}