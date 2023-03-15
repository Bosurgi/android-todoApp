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

    // Instantiating the recycler view
    private val tasksRecycler = binding.taskRecycler

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

        // Setting task list based on database data
        taskList = getTaskList(db.readAllData())

        // Setting the adapter for the recycler view
        tasksRecycler.adapter = TasksAdapter(this, taskList)

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

    } // End of onCreate

    /***
     * It gets all the tasks present in the database from cursor
     * @return A list of tasks to display
     */
    private fun getTaskList(cursor: Cursor): MutableList<Task> {

        // Instantiating the list of task
        val taskList: MutableList<Task> = mutableListOf()

            // Instantiating new Task
            val task: Task = Task()
            if(cursor.count == 0) {
                Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show()
            }
            else {
                // Index starting from -1 - Looping through all the cursor's elements
                while(cursor.moveToNext()){
                        task.id = cursor.getInt(0)
                        task.isDone = cursor.getInt(1)
                        task.name = cursor.getString(2)

                        // Adding the task to the list
                        taskList.add(task)
                    }
                }
        cursor.close()
        return taskList
    } // End of method

    /**
     * On on Destroy closing the database handler
     */
    override fun onDestroy() {
        db.close()
        super.onDestroy()
    }
}