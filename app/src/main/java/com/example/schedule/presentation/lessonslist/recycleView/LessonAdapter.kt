package com.example.schedule.presentation.lessonslist.recycleView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.data.database.room.dao.LessonDao
import com.example.schedule.data.database.room.entities.LessonDB
import com.example.schedule.databinding.LessonAdapterViewHolderBinding

class LessonAdapter : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    var listOfLessons = listOf<LessonVO>()
    var lessonDao: LessonDao? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder(
            LessonAdapterViewHolderBinding
                .inflate(LayoutInflater.from(parent.context), parent, false), lessonDao
        )
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = listOfLessons[position]

        holder.bind(lesson)
        holder.setOnClickListeners(lesson)
    }

    //don't repeat yourself - DRY

    override fun getItemCount(): Int {
        return listOfLessons.size
    }

    //Здесь ViewHolder принимает дизайн (binding)
    class LessonViewHolder(
        private val binding: LessonAdapterViewHolderBinding,
        private val lessonDao: LessonDao?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(lessonVO: LessonVO) {
            binding.tvLessonTitle.text = lessonVO.lessonTitle
            binding.tvLessonLastChangeTime.text = lessonVO.lessonLastChangedTime
        }

        fun setOnClickListeners(currentLesson: LessonDB) {
            binding.ivDeleteLesson.setOnClickListener {
                lessonDao?.deleteLesson(currentLesson)
            }
        }
    }
}