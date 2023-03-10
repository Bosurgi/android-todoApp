package com.leedsbeckett.todo_application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.leedsbeckett.todo_application.databinding.ActivityMainBinding
import com.leedsbeckett.todo_application.databinding.NewTaskBinding

class AddTask : AppCompatActivity() {

    private lateinit var binding: NewTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = NewTaskBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

    }

}