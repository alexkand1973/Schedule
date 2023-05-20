package com.example.schedule.common.app

import android.app.Application
import com.example.schedule.data.database.room.database.AppDatabase

//чтобы не прописывать каждый раз INSTANCE создаем класс
// и в манифесте задаем ему имя android:name=".common.app.App"
class App : Application() {

    val database = AppDatabase.getDatabase(this)
    val lessonsDao = database.getLessonsDao()
}