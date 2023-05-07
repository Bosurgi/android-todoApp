package com.leedsbeckett.todo_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.leedsbeckett.todo_application.databinding.ActivityMainBinding
import com.leedsbeckett.todo_application.fragments.TaskRecyclerFragment
import com.leedsbeckett.todo_application.utils.DatabaseHandler

const val TAG = "Main Activity"
const val BUNDLE_PAGE = "main"

class MainActivity : AppCompatActivity() {

    // Initialising binding variable
    private lateinit var binding: ActivityMainBinding

    // Initialising the fragment
    private lateinit var fragment: TaskRecyclerFragment

    // Instantiating database
    private val db = DatabaseHandler(this)

    // Flag for main page selected
    private var isMain: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        // Initialising binding variable
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        // Setting up the layout view
        val view = binding.root
        setContentView(view)
        // Initialising the Navigation Bar
        val navBar = binding.navigationBar

        // The fragment is initialised based on the saved state, this indicates what page is open
        fragment = if(savedInstanceState != null) {
            TaskRecyclerFragment.newInstance(savedInstanceState.getBoolean(BUNDLE_PAGE))
        } else {
            TaskRecyclerFragment.newInstance(true)
        }

        // Adding the fragment to the view
        val fm = supportFragmentManager
        fm.beginTransaction()
                // Replace is used to avoid adding multiple fragment on the view
            .replace(binding.fragmentContainer.id, fragment)
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
                    isMain = true
                    true
                }
                // When on completed task menu it shows only completed tasks
                R.id.completed_task -> {
                    setFragment(TaskRecyclerFragment.newInstance(false))
                    isMain = false
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

    /**
     * It saves the instance state storing the flag indicating if
     * the main page is selected or not.
     * This allows restoring the right view when screen rotated.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(BUNDLE_PAGE, isMain)
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