package com.leedsbeckett.todo_application.utils

import android.graphics.Color
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.leedsbeckett.todo_application.adapter.TasksAdapter
/**
 * It represents a Item touch helper which will manage the calls
 * to perform action such as swipe and drag and drop with custom options
 * @see ItemTouchHelper
 */
class CustomItemTouchHelper(private val adapter: TasksAdapter) :
    ItemTouchHelper.Callback() {

    // Getter for the moving flags
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        // Setting the swipe flags to left or right
        val swipeFlag: Int = ItemTouchHelper.START or ItemTouchHelper.END
        // Returns the flags with the allowed movements (swipe)
        return makeMovementFlags(0, swipeFlag)
    }

    // Disabling Move
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    // Enabling the swipe option
    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    // Disabling drag and drop option
    override fun isLongPressDragEnabled(): Boolean {
        return false
    }

    // Listener for Swipe events
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // Initialising the position of the item from the adapter
        val position: Int = viewHolder.adapterPosition
        // Calling the adapter Swipe function inherited from the Interface
        adapter.onItemSwipe(position)

    }

    // Changing the colour to highlight the selected item
    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            viewHolder?.itemView?.setBackgroundColor(Color.LTGRAY)
        }
    }

}