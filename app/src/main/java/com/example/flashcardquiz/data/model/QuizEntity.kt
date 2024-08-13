package com.example.flashcardquiz.data.model

import android.icu.text.CaseMap.Title
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken

@Entity(tableName = "quiz_table")

data class QuizEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int?,
    val category: String,
    val ques: String,
    val correctAnswer: String,
    val incorrectAnswer : List<String>
)

class Converters {
    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>(){}.type
        return Gson().fromJson(value, listType)
    }
    @TypeConverter
    fun fromList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}

