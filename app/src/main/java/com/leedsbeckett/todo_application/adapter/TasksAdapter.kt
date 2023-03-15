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

        // Inflating the list of tasks represented by Checkbox
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.tasks_item, parent, false)
        // Returning the view holder with the adapter layout
        return TaskViewHolder(adapterLayout)
    }

    /***
     * Gets the number of items in the tasks
     */
    override fun getItemCount(): Int {
        return data.size
    }

    /***
     * Method called by layout manager to replace content of an object in the list
     */
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        // Fetching the item from the dataset - List of Task at specified index
        val item: Task = data[position]
        // Setting the name
        holder.taskView.text = item.name
        // Setting the task if performed to tick the checkbox
        item.isDone = if (holder.taskView.isChecked) 1 else 0
    }

}