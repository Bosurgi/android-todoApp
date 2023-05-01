package com.leedsbeckett.todo_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.leedsbeckett.todo_application.adapter.TasksAdapter
import com.leedsbeckett.todo_application.databinding.ActivityMainBinding
import com.leedsbeckett.todo_application.fragments.TaskRecyclerFragment
import com.leedsbeckett.todo_application.model.Task
import com.leedsbeckett.todo_application.utils.CustomItemTouchHelper
import com.leedsbeckett.todo_application.utils.DatabaseHandler

const val TAG = "Main Activity"

class MainActivity : AppCompatActivity() {

    // Instantiating binding variable
    private lateinit var binding: ActivityMainBinding

    // Instantiating database
    private val db = DatabaseHandler(this)

    // Instantiating temporary the list of task
//    private var taskList: MutableList<Task> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {

        // Initialising binding variable
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        // Setting up the layout view
        val view = binding.root
        setContentView(view)
        // Instantiating the fragment
        val fragment: TaskRecyclerFragment = TaskRecyclerFragment.newInstance()

        val fm = supportFragmentManager
        fm.beginTransaction()
            .add(binding.fragmentContainer.id, fragment)
            .commitNow()
//        val cursor = db.readAllData()
        // Setting task list based on database data
//        taskList = db.getTaskList(cursor)

        // Instantiating Add button
        val buttonAdd = binding.buttonAdd
        // Instantiating Clear button
        val buttonClear = binding.clearButton

        // Add button Listener
        buttonAdd.setOnClickListener{
            val intent = Intent(this, AddTaskHost::class.java)

            // Checking if Activity is null
            if(intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
            // If there is no activity, log message will be displayed
            else {
                Log.d(TAG, "Activity not present")}
        }

        // Clear All Listener
        buttonClear.setOnClickListener {
            // Clear all data
            db.deleteAllData()
            // Updating the recycler view
//            taskList = db.getTaskList(db.readAllData())
//            binding.taskRecycler.adapter = TasksAdapter(this, taskList)
        }
    } // End of onCreate


    // When rotating screen or on another Activity, on resume is called to restore the view
    override fun onResume() {
        super.onResume()
        // Update the task List after adding entry
//        taskList = db.getTaskList(db.readAllData())
//        binding.taskRecycler.adapter = TasksAdapter(this, taskList)
    }

    /**
     * On on Destroy closing the database handler
     */
    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }
}