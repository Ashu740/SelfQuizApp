package com.example.flashcardquiz.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.flashcardquiz.data.model.QuizEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface QuizDao {
    @Query("SELECT * FROM quiz_table")
    fun getAllQuiz(): Flow<List<QuizEntity>>

    @Query("SELECT * FROM quiz_table WHERE category = :category")
    fun getQuizByCategory(category: String): Flow<List<QuizEntity>>

    @Upsert
    suspend fun insertQuiz(quizEntity: QuizEntity)

    @Delete
    suspend fun deleteQuiz(quizEntity: QuizEntity)

    @Update
    suspend fun updateQuiz(quizEntity: QuizEntity)
}