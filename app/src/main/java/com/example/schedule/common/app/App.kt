package com.example.schedule.common.app

import android.app.Application
import com.example.schedule.data.database.room.database.AppDatabase

class App : Application() {

    val database = AppDatabase.getDatabase(this)
    val lessonsDao = database.getLessonsDao()
}