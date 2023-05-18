package com.example.schedule.presentation.lessonslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedule.R
import com.example.schedule.databinding.FragmentListBinding
import com.example.schedule.presentation.AddLessonFragment
import com.example.schedule.presentation.lessonslist.recycleView.LessonAdapter

class ListFragment : Fragment() {

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

    fun initLessonAdapter() {
        lessonsAdapter = LessonAdapter()
       // lessonsAdapter.listOfLessons = here will be data from database
        binding?.rvLessons?.adapter = lessonsAdapter
        binding?.rvLessons?.layoutManager = LinearLayoutManager(requireContext())
    }
}