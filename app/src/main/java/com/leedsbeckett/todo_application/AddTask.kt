package com.leedsbeckett.todo_application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.leedsbeckett.todo_application.databinding.ActivityMainBinding
import com.leedsbeckett.todo_application.databinding.NewTaskBinding
import com.leedsbeckett.todo_application.model.Task
import com.leedsbeckett.todo_application.utils.DatabaseHandler

class AddTask : AppCompatActivity() {

    private lateinit var binding: NewTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Setting the binding
        binding = NewTaskBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        // Display the activity
        setContentView(view)

        // Setting on Button click listener
        val buttonClick = binding.addButton
        buttonClick.setOnClickListener {
            // Instantiating database
            val db: DatabaseHandler = DatabaseHandler(this)
            // Creating task and setting it to status 0 - Not complete
            val task = Task(binding.todoTask.text.toString().trim(), 0)
            // Adding the task to database
            db.addTask(task)

            // Showing message if text is not empty
            if (binding.todoTask.text.toString() != ""){
                val snackbar = Snackbar.make(view, R.string.task_added, Snackbar.LENGTH_SHORT)
                snackbar.show()
            }
            else {
                val snackbar = Snackbar.make(view, R.string.task_empty, Snackbar.LENGTH_SHORT)
                snackbar.show()
            }
            binding.todoTask.text.clear()
        }
    }

}