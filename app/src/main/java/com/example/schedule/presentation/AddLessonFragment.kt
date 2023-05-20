package com.example.schedule.presentation

import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.schedule.common.app.App
import com.example.schedule.common.utils.extentions.getApp
import com.example.schedule.common.utils.extentions.getLessonDao
import com.example.schedule.data.database.room.entities.LessonDB
import com.example.schedule.databinding.FragmentAddLessonBinding
import java.text.SimpleDateFormat
import java.util.*

class AddLessonFragment : Fragment() {

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

        setOnClickListeners()
    }

    //Добавление в базу данных
    private fun setOnClickListeners() {
        binding?.btnSaveLesson?.setOnClickListener {
            val lessonTitle = binding?.etLessonTitle?.text.toString()
            val lessonDescription = binding?.etLessonDescription?.text.toString()
            val lessonTeacher = binding?.etTeachersName?.text.toString()

            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.getDefault())
            val currentDate = sdf.format(Date())


            getLessonDao().insertLesson(
                LessonDB(
                    lessonTitle = lessonTitle,
                    lessonDescription = lessonDescription,
                    lessonTeacher = lessonTeacher,
                    lessonLastChangedTime = currentDate
                )
            )
        }
    }
}