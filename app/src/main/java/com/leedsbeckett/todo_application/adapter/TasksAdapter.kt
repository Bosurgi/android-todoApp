package com.leedsbeckett.todo_application.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leedsbeckett.todo_application.R
import com.leedsbeckett.todo_application.model.Task

class TasksAdapter(private val context: Context, private val data: List<Task>)
    : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>(){

    // View holder nested class to hold items
    class TaskViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        // Fetching the view from the layout
        val taskView: CheckBox = view.findViewById(R.id.taskCheckbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        // Inflating the list of tasks
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.tasks_item, parent, false)
        // Returning the view holder with the adapter layout
        return TaskViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    /***
     * Method called by layout manager to replace content of an object in the list
     */
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}