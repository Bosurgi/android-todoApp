package com.leedsbeckett.todo_application.fragments

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.leedsbeckett.todo_application.R
import com.leedsbeckett.todo_application.adapter.TasksAdapter
import com.leedsbeckett.todo_application.databinding.FragmentTaskRecyclerBinding
import com.leedsbeckett.todo_application.model.Task
import com.leedsbeckett.todo_application.utils.CustomItemTouchHelper
import com.leedsbeckett.todo_application.utils.DatabaseHandler

/**
 * A simple [Fragment] subclass.
 * Use the [TaskRecyclerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskRecyclerFragment : Fragment() {
    // Setting up the binding to inflate the Recycler view
    private lateinit var binding: FragmentTaskRecyclerBinding
    private lateinit var taskList: MutableList<Task>
    private lateinit var db: DatabaseHandler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskRecyclerBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = DatabaseHandler(view.context)
        taskList = db.getTaskList(db.readAllData())

        // Instantiating the recycler view from the fragment
        val taskRecycler = binding.taskRecyclerFragment
        // Setting the Layout Manager
        val lm : LinearLayoutManager = LinearLayoutManager(context)
        // Assigning the layout manager with the Recycler view
        taskRecycler.layoutManager = lm
        taskRecycler.adapter = TasksAdapter(view.context, taskList)

        // Instantiating an anonymous object which inherits from CustomTouchHandler
        val swipeHandler = object : CustomItemTouchHelper(view.context){

            // Overriding the onSwipe listener
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // Setting the adapter
                val adapter = taskRecycler.adapter as TasksAdapter
                // Remove the item at the adapter's position
                adapter.removeItemAt(viewHolder.adapterPosition)
            }
        }
        // Attaching the ItemTouchHandler to the recycler view
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(taskRecycler)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment TaskRecyclerFragment.
         */
        fun newInstance(): TaskRecyclerFragment{
            return TaskRecyclerFragment()
        }
    }
}