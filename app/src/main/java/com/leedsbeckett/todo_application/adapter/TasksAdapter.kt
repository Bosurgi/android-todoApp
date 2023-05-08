package com.leedsbeckett.todo_application.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.leedsbeckett.todo_application.R
import com.leedsbeckett.todo_application.model.Task
import com.leedsbeckett.todo_application.utils.DatabaseHandler
import com.leedsbeckett.todo_application.utils.IonTaskClickListener

class TasksAdapter(private val context: Context, private val data: MutableList<Task>,
                   private var listener: IonTaskClickListener?)
    : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    private val db = DatabaseHandler(this.context)

    // View holder nested class to hold items
    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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

        if (item.status == 1) {
            holder.taskView.isChecked = true
        }

        // Setting the listener when an item is clicked using the interface
        holder.itemView.setOnClickListener {
            listener?.onTaskClicked(item)
        }

        // CheckBox listener
        holder.taskView.setOnCheckedChangeListener{ _: CompoundButton, isChecked: Boolean ->
            // When checked the task status will be updated
            if(isChecked) {
                db.updateStatus(item.id, 1)
                Toast.makeText(this.context, item.name + " done", Toast.LENGTH_LONG).show()
            }
            // When UnChecked the task status will be reset
            else {
                db.updateStatus(item.id, 0)
                Toast.makeText(this.context, item.name + " unchecked", Toast.LENGTH_LONG).show()
            }
        }// Setting the name
        holder.taskView.text = item.name
    }

    /**
     * It removes an item from the position specified, updates the database
     * and notifies the recycler view of item's delete so it can update its position
     * @param position the index position of the item in the recycler view
     */
    fun removeItemAt(position: Int) {
        db.deleteData(data[position].id)
        data.removeAt(position)
        notifyItemRemoved(position)
    }
}