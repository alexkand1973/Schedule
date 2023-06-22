package com.example.schedule.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.schedule.R
import com.example.schedule.common.utils.extentions.getLessonsDao
import com.example.schedule.data.database.room.entities.LessonDB
import com.example.schedule.databinding.FragmentAddLessonBinding
import com.example.schedule.presentation.lessonslist.ListFragment.Companion.NAVIGATION_FROM_LESSON_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AddLessonFragment : Fragment() {
    companion object {
        const val MIN_SYMBOLS_NUMBER = 0
        const val DEFAULT_VALUE_FOR_NOTE_DB_ID = 0
    }

    private var isLessonCreated: Boolean? = false
    private var currentLesson: LessonDB? = null
    private var binding: FragmentAddLessonBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddLessonBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Доступ к View-элементу через переменную
//        val tvTest = requireActivity().findViewById<TextView>(R.id.tvTest)
//        tvTest.setOnClickListener {
//
//        }
        if (arguments?.getInt(NAVIGATION_FROM_LESSON_KEY) != null) {
            isLessonCreated = true

            val lessonDeferred = CoroutineScope(Dispatchers.IO).async {
                getLessonsDao()?.getLesson(arguments?.getInt(NAVIGATION_FROM_LESSON_KEY)!!)
            }
            CoroutineScope(Dispatchers.Main).launch {
                currentLesson = lessonDeferred.await()

                loadCurrentLessonData()
                binding?.btnSaveLesson?.text =
                    getString(R.string.btn_save_lesson_update_lesson_text_add_lesson_fragment)
            }
        }
        setOnClickListeners()
    }

    private fun loadCurrentLessonData() {
        binding?.etLessonTitle?.setText(currentLesson?.lessonTitle)
        binding?.etLessonDescription?.setText(currentLesson?.lessonDescription)
        binding?.etLessonTeacher?.setText(currentLesson?.lessonTeacher)
    }

    //Добавление в базу данных
    private fun setOnClickListeners() {
        binding?.btnSaveLesson?.setOnClickListener {
            if (binding?.etLessonTitle?.text?.length != MIN_SYMBOLS_NUMBER) {
                val lessonTitle = binding?.etLessonTitle?.text.toString()
                val lessonDescription = binding?.etLessonDescription?.text.toString()
                val lessonTeacher = binding?.etLessonTeacher?.text.toString()

                val currentDateInMills = System.currentTimeMillis()

                CoroutineScope(Dispatchers.IO).launch {
                    val lesson = LessonDB(
                        id = currentLesson?.id ?: DEFAULT_VALUE_FOR_NOTE_DB_ID,
                        lessonTitle = lessonTitle,
                        lessonDescription = lessonDescription,
                        lessonTeacher = lessonTeacher,
                        lessonLastChangedTime = currentDateInMills.toString()
                    )

                    if (isLessonCreated == true) {
                        getLessonsDao()?.updateLesson(lesson)
                    } else {
                        getLessonsDao()?.insertLesson(lesson)
                    }
                }
                requireActivity().supportFragmentManager.apply {
                    popBackStack()
                    beginTransaction().replace(
                        R.id.fragmentContainer,
                        com.example.schedule.presentation.lessonslist.ListFragment()
                    ).commit()
                }
                //вторым аргументом в replace каккой ListFragment брать?
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.empty_lesson_title_toast_text_add_lesson_fragment),
                    Toast.LENGTH_LONG
                ).show()

            }
        }
    }
}