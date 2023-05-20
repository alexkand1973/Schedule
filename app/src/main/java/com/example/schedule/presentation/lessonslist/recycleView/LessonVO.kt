package com.example.schedule.presentation.lessonslist.recycleView

import com.example.schedule.data.database.room.entities.LessonDB

data class LessonVO(
    val lessonTitle: String,
    val lessonDescription: String,
    val lessonTeacher: String,
    val lessonLastChangedTime: String
)

fun LessonVO.toLessonDB(): LessonDB {
    return LessonDB(
        lessonTitle = lessonTitle,
        lessonDescription = lessonDescription,
        lessonTeacher = lessonTeacher,
        lessonLastChangedTime = lessonLastChangedTime
    )
}