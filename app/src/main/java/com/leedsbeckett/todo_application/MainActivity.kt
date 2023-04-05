package com.leedsbeckett.todo_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import com.leedsbeckett.todo_application.adapter.TasksAdapter
import com.leedsbeckett.todo_application.databinding.ActivityMainBinding
import com.leedsbeckett.todo_application.model.Task
import com.leedsbeckett.todo_application.utils.CustomItemTouchHelper
import com.leedsbeckett.todo_application.utils.DatabaseHandler
import com.leedsbeckett.todo_application.utils.ItemTouchHelperAdapter

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

        // Instantiating Add button
        val buttonAdd = binding.buttonAdd
        // Instantiating Clear button
        val buttonClear = binding.clearButton

        // Add button Listener
        buttonAdd.setOnClickListener{
            val intent = Intent(this, AddTask::class.java)

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
            taskList = db.getTaskList(db.readAllData())
            binding.taskRecycler.adapter = TasksAdapter(this, taskList)
        }
        val adapter = TasksAdapter(this, taskList)

        // Setting the adapter for the recycler view
        tasksRecycler.adapter = adapter

        // Attaching the callback to the recyclerview
        val callback: ItemTouchHelper.Callback = CustomItemTouchHelper(adapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(tasksRecycler)

    } // End of onCreate


    override fun onResume() {
        super.onResume()
        // Update the task List after adding entry
        taskList = db.getTaskList(db.readAllData())
        binding.taskRecycler.adapter = TasksAdapter(this, taskList)
    }

    /**
     * On on Destroy closing the database handler
     */
    override fun onDestroy() {
        super.onDestroy()
        // TODO: Implementing screen rotation
        db.close()
    }
}