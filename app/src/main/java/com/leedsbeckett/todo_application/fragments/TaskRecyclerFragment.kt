package com.leedsbeckett.todo_application.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leedsbeckett.todo_application.adapter.TasksAdapter
import com.leedsbeckett.todo_application.databinding.FragmentTaskRecyclerBinding
import com.leedsbeckett.todo_application.model.Task
import com.leedsbeckett.todo_application.utils.CustomItemTouchHelper
import com.leedsbeckett.todo_application.utils.DatabaseHandler
import com.leedsbeckett.todo_application.utils.IonTaskClickListener

/**
 * A simple [Fragment] subclass.
 * Use the [TaskRecyclerFragment.newInstance] factory method to
 * create an instance of this fragment.
 * @param isAll flag to check if the data requested is all or only tasks done
 */

// Constant used as the key for the bundle containing the flag displaying for all data
private const val BUNDLE_KEY = "all"
class TaskRecyclerFragment() : Fragment(), IonTaskClickListener {
    // Setting up the binding to inflate the Recycler view
    private lateinit var binding: FragmentTaskRecyclerBinding
    private lateinit var taskList: MutableList<Task>
    private lateinit var db: DatabaseHandler
    private lateinit var fm: FragmentManager
    private var isAll: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isAll = requireArguments().getBoolean(BUNDLE_KEY)
    }

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

        taskList = if (this.isAll) {
            db.getTaskList(db.readAllData())
        } else {
            db.getTaskList(db.readCompletedTask())
        }

        // Instantiating the recycler view from the fragment
        val taskRecycler = binding.taskRecyclerFragment
        // Setting the Layout Manager
        val lm : LinearLayoutManager = LinearLayoutManager(context)
        // Assigning the layout manager with the Recycler view
        taskRecycler.layoutManager = lm
        taskRecycler.adapter = TasksAdapter(view.context, taskList, this)

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
         * @param isAll flag to check if the data requested is all or only tasks done
         * @return A new instance of fragment TaskRecyclerFragment.
         */
        fun newInstance(isAll: Boolean): TaskRecyclerFragment{
            val bundle = Bundle(1)
            val taskRecyclerFragment = TaskRecyclerFragment()
            bundle.putBoolean(BUNDLE_KEY, isAll)
            taskRecyclerFragment.arguments = bundle
            return taskRecyclerFragment
        }
    }
    override fun onTaskClicked(task: Task) {
        val detailFragment = TaskDetailDialogFragment.newInstance()
        detailFragment.show(parentFragmentManager, "Detail Dialog")
    }
}