package com.example.schedule.presentation.lessonslist

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedule.R
import com.example.schedule.common.utils.extentions.getLessonsDao
import com.example.schedule.data.database.room.entities.toLessonVO
import com.example.schedule.databinding.FragmentListBinding
import com.example.schedule.presentation.AddLessonFragment
import com.example.schedule.presentation.lessonslist.recycleView.LessonAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ListFragment : androidx.fragment.app.Fragment() {

    companion object {
        const val TIME_FORMAT = "dd/M/yyyy hh:mm:ss"
        const val NAVIGATION_FROM_LESSON_KEY = "LESSON_ID"
    }

    private var binding: FragmentListBinding? = null
    private var lessonsAdapter: LessonAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setClickListeners()
        initLessonAdapter()
    }

    private fun setClickListeners() {
        //По нажатию на кнопку делаем переход на фрагмент урока
        binding?.fabAddLesson?.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction().apply {
                    addToBackStack(null)
                        .replace(R.id.fragmentContainer, AddLessonFragment()).commit()
                }
        }
    }

    private fun initLessonAdapter() {
        lessonsAdapter = LessonAdapter()
        //here will be data from database
        CoroutineScope(Dispatchers.IO).launch {

            lessonsAdapter?.listOfLessons = getLessonsDao()?.getAllLessons()
                ?.sortedByDescending { lessonDB ->
                lessonDB.lessonLastChangedTime.toLong()
            }?.map { lessonDb ->
                    val lesson = lessonDb.toLessonVO()

                    val sdf = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
                    val currentDate = sdf.format(lesson.lessonLastChangedTime.toLong())

                    lesson.lessonLastChangedTime = currentDate
                    lesson
                }?.toMutableList()!!
        }

        lessonsAdapter?.onLessonClicked = { lessonId ->
            val bundle = Bundle()
            bundle.putInt("LESSON_ID", lessonId)

            val fragment = AddLessonFragment()
            fragment.arguments = bundle

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment).addToBackStack(null).commit()
        }

        lessonsAdapter?.lessonDao = getLessonsDao()
        binding?.rvLessons?.adapter = lessonsAdapter
        binding?.rvLessons?.layoutManager = LinearLayoutManager(requireContext())
    }
}