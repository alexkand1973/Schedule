package com.example.schedule.presentation

import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.schedule.databinding.FragmentAddLessonBinding

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
    }
}