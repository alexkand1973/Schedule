package com.example.schedule.data.database.room.dao

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface LessonDao {

    //Помещаем урок
    @Insert
    fun insertLesson(Lesson: LessonDB) {

    }
}