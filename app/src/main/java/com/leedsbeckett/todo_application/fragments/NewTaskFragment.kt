package com.leedsbeckett.todo_application.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.leedsbeckett.todo_application.R
import com.leedsbeckett.todo_application.databinding.NewTaskBinding
import com.leedsbeckett.todo_application.model.Task
import com.leedsbeckett.todo_application.utils.DatabaseHandler

class NewTaskFragment : Fragment() {
    // Setting up the binding to inflate the New Task page
    private lateinit var binding: NewTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewTaskBinding.inflate(inflater, container, false)
        // Returning the view
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonClick = binding.addButton
        buttonClick.setOnClickListener {
            // Instantiating database
            val db: DatabaseHandler = DatabaseHandler(view.context)
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

    companion object {
        fun newInstance(): NewTaskFragment {
            return NewTaskFragment()
        }
    }
}