package com.example.schedule.presentation.lessonslist.recycleView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.data.database.room.dao.LessonDao
import com.example.schedule.data.database.room.entities.LessonDB
import com.example.schedule.databinding.LessonAdapterViewHolderBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LessonAdapter : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    var listOfLessons: MutableList<LessonVO> = mutableListOf()
    var lessonDao: LessonDao? = null
    var onLessonClicked: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder(
            LessonAdapterViewHolderBinding
                .inflate(LayoutInflater.from(parent.context), parent, false), lessonDao,
            onLessonClicked
        )
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = listOfLessons[position]

        holder.bind(lesson)

        CoroutineScope(Dispatchers.IO).launch {
            lessonDao?.getLesson(lesson.id)?.let { lessonDB ->
                withContext(Dispatchers.Main) {
                    holder.setOnClickListeners(lessonDB) { deleteLessonFromList(lesson) }
                }
            }
        }
    }

    //don't repeat yourself - DRY

    override fun getItemCount(): Int {
        return listOfLessons.size
    }

    private fun deleteLessonFromList(lessonVo: LessonVO) {
        val indexToDelete = listOfLessons.indexOfFirst { currentLesson -> currentLesson.id == lessonVo.id }
        if (indexToDelete != -1) {
            listOfLessons.removeAt(indexToDelete)
            notifyItemRemoved(indexToDelete)
        }
    }

    //Здесь ViewHolder принимает дизайн (binding)
    class LessonViewHolder(
        private val binding: LessonAdapterViewHolderBinding,
        private val lessonDao: LessonDao?,
        private val onLessonClicked: ((Int) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(lessonVO: LessonVO) {
            binding.tvLessonTitle.text = lessonVO.lessonTitle
            binding.tvLessonLastChangeTime.text = lessonVO.lessonLastChangedTime
        }

        fun setOnClickListeners(
            currentLesson: LessonDB,
            onDeleteLessonClicked: () -> Unit
        ) {
            //по нажатию на любой элемент framelayout (root) выполняем действия:
            binding.root.setOnClickListener {
                //навигвция на фрагмент с передачей данных,
                // ответственность за передачу отдаем фрагменту, используя лямбду: onLessonClicked
                onLessonClicked?.let { onLessonClicked -> onLessonClicked(currentLesson.id) }
//                val bundle = Bundle()
//                bundle.putSerializable()
            }
            //по нажатию на delete урок удаляется
            binding.ivDeleteLesson.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    lessonDao?.deleteLesson(currentLesson)
                }
                onDeleteLessonClicked()
            }
        }
    }
}