package com.example.flashcardquiz.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.flashcardquiz.data.dao.QuizDao
import com.example.flashcardquiz.data.model.Converters
import com.example.flashcardquiz.data.model.QuizEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [QuizEntity::class], version = 2)
@TypeConverters(Converters::class)
abstract class QuizDataBase :RoomDatabase(){

    abstract fun quizDao() : QuizDao

    companion object{
        private const val DATABASE_NAME = "quiz-database"

        @JvmStatic
        fun getDatabase(context: Context):QuizDataBase{
            return Room.databaseBuilder(
                context,
                QuizDataBase::class.java,
                DATABASE_NAME,
            ).fallbackToDestructiveMigration()
                .addCallback(object :RoomDatabase.Callback(){
                override fun onCreate(db: SupportSQLiteDatabase){
                    super.onCreate(db)
                    CoroutineScope(Dispatchers.IO).launch {
                        val dao = getDatabase(context).quizDao()

                        dao.insertQuiz(QuizEntity(1,"math","2+2=?","4", listOf("2", "3", "5")))
                        dao.insertQuiz(QuizEntity(2,"math","2+2=?","4", listOf("2", "3", "5")))
                        dao.insertQuiz(QuizEntity(3,"math","2+2=?","4", listOf("2", "3", "5")))
                        dao.insertQuiz(QuizEntity(4,"math","2+2=?","4", listOf("2", "3", "5")))
                    }
                }
                }).build()
        }
    }
}
