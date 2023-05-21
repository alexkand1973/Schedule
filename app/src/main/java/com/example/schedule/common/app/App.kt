package com.example.schedule.common.app

import android.app.Application
import com.example.schedule.data.database.room.dao.LessonDao
import com.example.schedule.data.database.room.database.AppDatabase

//чтобы не прописывать каждый раз INSTANCE создаем класс
// и в манифесте задаем ему имя android:name=".common.app.App"
class App : Application() {

    var database: AppDatabase? = null
    var lessonsDao: LessonDao? = null
    override fun onCreate() {
        database = AppDatabase.getDatabase(this)
        lessonsDao = database?.getLessonsDao()

        super.onCreate()
    }
}