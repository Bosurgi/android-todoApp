package com.leedsbeckett.todo_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        // Setting button click listener to open new activity
        val buttonClick = binding.buttonAdd
        buttonClick.setOnClickListener{
            val intent = Intent(this, AddTask::class.java)

            // Checking if Activity is null
            if(intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
            else {
                Log.d("Main Activity", "Activity not present")}

        }
    }
}