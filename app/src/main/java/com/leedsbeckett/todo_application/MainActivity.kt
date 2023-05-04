package com.leedsbeckett.todo_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
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

    override fun onCreate(savedInstanceState: Bundle?) {

        // Initialising binding variable
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        // Setting up the layout view
        val view = binding.root
        setContentView(view)
        // Initialising the Navigation Bar
        val navBar = binding.navigationBar

        // Instantiating the fragment
        val fragment: TaskRecyclerFragment = TaskRecyclerFragment.newInstance(true)

        // Adding the fragment to the view
        val fm = supportFragmentManager
        fm.beginTransaction()
            .add(binding.fragmentContainer.id, fragment)
            .commitNow()

        // Instantiating Add button
        val buttonAdd = binding.buttonAdd
        // Instantiating Clear button
        val buttonClear = binding.clearButton

        // Nav Bar Listener
        navBar.setOnItemSelectedListener {
            item ->
            when(item.itemId) {
                // When home page it will show all the tasks
                R.id.home -> {
                    setFragment(TaskRecyclerFragment.newInstance(true))
                    true
                }
                // When on completed task menu it shows only completed tasks
                R.id.completed_task -> {
                    setFragment(TaskRecyclerFragment.newInstance(false))
                    true
                }
                else -> false
            }
        }
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
            // Replacing the fragment with updated data
            setFragment(fragment)
        }
    } // End of onCreate

    // When rotating screen or on another Activity, on resume is called to restore the view
    override fun onResume() {
        super.onResume()
        setFragment(TaskRecyclerFragment.newInstance(true))
    }
    /**
     * On on Destroy closing the database handler
     */
    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }

    /**
     * It Sets the current fragment with the one passed into the function
     * @param fragment the fragment we want to replace with
     */
    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainer.id, fragment)
            commitNow()
        }
    }
}