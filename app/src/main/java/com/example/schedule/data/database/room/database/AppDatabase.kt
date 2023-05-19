package com.example.schedule.data.database.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.schedule.data.database.room.dao.LessonDao
import com.example.schedule.data.database.room.entities.LessonDB

//В entities [] указываем через запятую количество таблиц в базе данных
@Database(entities = [LessonDB::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getLessonsDao(): LessonDao

    //Здесь создаем singleton - object и свойство INSTANCE
    companion object {

        @Volatile //аннотация для решения проблем кеширования данных
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, "lessons_database").build()
                INSTANCE = instance

                instance
            }
        }
    }
}