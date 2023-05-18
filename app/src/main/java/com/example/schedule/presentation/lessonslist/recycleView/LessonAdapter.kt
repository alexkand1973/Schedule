package com.example.schedule.presentation.lessonslist.recycleView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.databinding.LessonAdapterViewHolderBinding

class LessonAdapter: RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

var listOfLessonVOS = listOf<LessonVO>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder(LessonAdapterViewHolderBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(listOfLessonVOS[position])
    }

    override fun getItemCount(): Int {
        return listOfLessonVOS.size
    }

    //Здесь ViewHolder принимает дизайн (binding)
    class LessonViewHolder(private val binding: LessonAdapterViewHolderBinding)
        : RecyclerView.ViewHolder(binding.root) {

            fun bind(lessonVO: LessonVO) {
                binding.tvLessonTitle.text = lessonVO.lessonTitle
                binding.tvLessonlastChangeTime.text = lessonVO.lessonLastChangedTime
            }
        }
}