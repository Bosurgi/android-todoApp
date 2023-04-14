package com.leedsbeckett.todo_application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.leedsbeckett.todo_application.databinding.AddTaskContainerBinding
import com.leedsbeckett.todo_application.fragments.NewTaskFragment

class AddTaskHost : AppCompatActivity() {

    private lateinit var binding: AddTaskContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Setting the binding
        binding = AddTaskContainerBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        // Display the activity
        setContentView(view)

        val fragment: NewTaskFragment = NewTaskFragment.newInstance()
        val fm = supportFragmentManager
        if (savedInstanceState == null) {
            fm.beginTransaction()
                .add(binding.newTaskContainer.id, fragment)
                .commitNow()
        }
    }

}