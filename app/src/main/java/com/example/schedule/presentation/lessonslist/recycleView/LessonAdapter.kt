package com.example.schedule.presentation.lessonslist.recycleView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.databinding.FragmentAddLessonBinding
import com.example.schedule.databinding.LessonAdapterViewHolderBinding

class LessonAdapter: RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

var listOfLessons = listOf<Lesson>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder(LessonAdapterViewHolderBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(listOfLessons[position])
    }

    override fun getItemCount(): Int {
        return listOfLessons.size
    }

    //Здесь ViewHolder принимает дизайн (binding)
    class LessonViewHolder(private val binding: LessonAdapterViewHolderBinding)
        : RecyclerView.ViewHolder(binding.root) {

            fun bind(lesson: Lesson) {
                binding.tvLessonTitle.text = lesson.lessonTitle
                binding.tvLessonlastChangeTime.text = lesson.lessonLastChangedTime
            }
        }
}