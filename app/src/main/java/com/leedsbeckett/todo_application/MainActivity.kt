package com.leedsbeckett.todo_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.leedsbeckett.todo_application.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Instantiating binding variable
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        // Initialising binding variable
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        // Setting up the layout view
        val view = binding.root
        setContentView(view)
    }
}