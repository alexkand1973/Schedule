package com.example.schedule.data.database.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.schedule.presentation.lessonslist.recycleView.LessonVO

//создаем класс для передачи данных
@Entity(tableName = "lessons_table")
data class LessonDB(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "lesson_title") val lessonTitle: String,
    @ColumnInfo(name = "lesson_description") val lessonDescription: String,
    @ColumnInfo(name = "lesson_teacher") val lessonTeacher: String,
    @ColumnInfo(name = "lesson_last_changed_time") val lessonLastChangedTime: String
)

//Mapping, mapper - процесс создания одного типа данных из другого
fun LessonDB.toLessonVO(): LessonVO {
    return LessonVO(
        id = id, lessonTitle = lessonTitle,
        lessonDescription = lessonDescription,
        lessonTeacher = lessonTeacher,
        lessonLastChangedTime = lessonLastChangedTime
    )
}