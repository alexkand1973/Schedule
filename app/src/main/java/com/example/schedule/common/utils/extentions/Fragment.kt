package com.example.schedule.common.utils.extentions

import androidx.fragment.app.Fragment
import com.example.schedule.common.app.App
import com.example.schedule.data.database.room.dao.LessonDao

fun Fragment.getApp(): App {
    return (requireContext().applicationContext as App)
}

fun Fragment.getLessonDao(): LessonDao {
    return (requireContext().applicationContext as App).lessonsDao
}