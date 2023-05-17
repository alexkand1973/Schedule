package com.example.schedule.presentation.lessonslist.recycleView

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.databinding.FragmentAddLessonBinding
import com.example.schedule.databinding.LessonAdapterViewHolderBinding

class LessonAdapter: RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

var listOfLessons = listOf<Lesson>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {

    }
    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return listOfLessons.size
    }

    class LessonViewHolder(binding: LessonAdapterViewHolderBinding)
        : RecyclerView.ViewHolder(binding.root)
}