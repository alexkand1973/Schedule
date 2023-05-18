package com.example.schedule.data.database.room.dao

import androidx.room.*
import com.example.schedule.data.database.room.entities.LessonDB

@Dao
interface LessonDao {

    //Помещаем урок
    @Insert
    fun insertLesson(lesson: LessonDB) {

    }

    //Обновление урока
    @Update
    fun updateLesson(lesson: LessonDB) {

    }

    //Удаляем урок
    @Delete
    fun deleteLesson(lesson: LessonDB) {

    }

    //Возвращаем урок
    @Query("")
    fun getLessonId(lessonId: Int) {

    }

    //Возвращаем расписание
    @Query("")
    fun getAllLessonList(): List<LessonDB>
}