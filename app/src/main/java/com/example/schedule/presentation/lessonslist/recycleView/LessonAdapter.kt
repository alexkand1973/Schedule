package com.example.schedule.presentation.lessonslist.recycleView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.data.database.room.dao.LessonDao
import com.example.schedule.data.database.room.entities.LessonDB
import com.example.schedule.databinding.LessonAdapterViewHolderBinding

class LessonAdapter : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    var listOfLessons = listOf<LessonVO>()
    var lessonDao: LessonDao? = null
    var onLessonClicked: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder(
            LessonAdapterViewHolderBinding
                .inflate(LayoutInflater.from(parent.context), parent, false), lessonDao,
             onLessonClicked)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = listOfLessons[position]

        holder.bind(lesson)
        lessonDao?.getLesson(lesson.id)?.let { lessonDB ->
            holder.setOnClickListeners(lessonDB)
        }
    }

    //don't repeat yourself - DRY

    override fun getItemCount(): Int {
        return listOfLessons.size
    }

    //Здесь ViewHolder принимает дизайн (binding)
    class LessonViewHolder(
        private val binding: LessonAdapterViewHolderBinding,
        private val lessonDao: LessonDao?,
        private val onLessonClicked: (() -> Unit)?)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(lessonVO: LessonVO) {
            binding.tvLessonTitle.text = lessonVO.lessonTitle
            binding.tvLessonLastChangeTime.text = lessonVO.lessonLastChangedTime
        }

        fun setOnClickListeners(currentLesson: LessonDB) {
            //по нажатию на любой элемент framelayout (root) выполняем действия:
            binding.root.setOnClickListener {
                //навигвция на фрагмент с передачей данных,
                // ответственность за передачу отдаем фрагменту, используя лямбду: onLessonClicked
                onLessonClicked?.let { onLessonClicked -> onLessonClicked() }
//                val bundle = Bundle()
//                bundle.putSerializable()
            }
            //по нажатию на delete урок удаляется
            binding.ivDeleteLesson.setOnClickListener {
                lessonDao?.deleteLesson(currentLesson)
            }
        }
    }
}