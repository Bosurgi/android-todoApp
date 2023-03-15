package com.leedsbeckett.todo_application

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.util.Log
import android.widget.Toast
import com.leedsbeckett.todo_application.adapter.TasksAdapter
import com.leedsbeckett.todo_application.databinding.ActivityMainBinding
import com.leedsbeckett.todo_application.model.Task
import com.leedsbeckett.todo_application.utils.DatabaseHandler

const val TAG = "Main Activity"

class MainActivity : AppCompatActivity() {

    // Instantiating binding variable
    private lateinit var binding: ActivityMainBinding

    // Instantiating database
    private val db = DatabaseHandler(this)

    // Instantiating temporary the list of task
    private var taskList: MutableList<Task> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {

        // Initialising binding variable
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        // Setting up the layout view
        val view = binding.root
        setContentView(view)

        val cursor = db.readAllData()
        // Setting task list based on database data
        taskList = db.getTaskList(cursor)

        // Instantiating the recycler view
        val tasksRecycler = binding.taskRecycler

        // Setting button click listener to open new activity
        val buttonClick = binding.buttonAdd
        buttonClick.setOnClickListener{
            val intent = Intent(this, AddTask::class.java)

            // Checking if Activity is null
            if(intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
            // If there is no activity, log message will be displayed
            else {
                Log.d(TAG, "Activity not present")}

        }

        // Setting the adapter for the recycler view
        tasksRecycler.adapter = TasksAdapter(this, taskList)

    } // End of onCreate


    /**
     * On on Destroy closing the database handler
     */
    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }
}