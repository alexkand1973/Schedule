package com.example.schedule.data.database.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//создаем класс для передачи данных
@Entity
data class LessonDB(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "lesson_title") val lessonTitle: String,
    @ColumnInfo(name = "lesson_description") val lessonDescription: String,
    @ColumnInfo(name = "lesson_teacher") val lessonTeacher: String) {

}
