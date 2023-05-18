package com.example.schedule.data.database.room.dao

import androidx.room.*
import com.example.schedule.data.database.room.entities.LessonDB


@Dao
interface LessonDao {

    //Помещаем урок
    @Insert
    fun insertLesson(lesson: LessonDB)

    //Обновление урока
    @Update
    fun updateLesson(lesson: LessonDB)

    //Удаляем урок
    @Delete
    fun deleteLesson(lesson: LessonDB)

    //Возвращаем урок
    @Query("SELECT * FROM lessons_table WHERE id =:lessonId")
    fun getLessonId(lessonId: Int)

    //Возвращаем расписание
    @Query("SELECT * FROM lessons_table WHERE ")
    fun getAllLessonList(): List<LessonDB>
}