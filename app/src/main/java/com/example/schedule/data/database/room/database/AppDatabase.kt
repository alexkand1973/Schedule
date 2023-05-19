package com.example.schedule.data.database.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.schedule.data.database.room.entities.LessonDB

@Database(entities = [LessonDB::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

}