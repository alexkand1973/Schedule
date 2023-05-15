package com.example.schedule.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.ListFragment
import com.example.schedule.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Делаем навигацию между фрагментами:
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, ListFragment()).commit()
    }
}