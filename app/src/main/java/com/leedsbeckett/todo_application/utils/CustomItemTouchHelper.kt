package com.leedsbeckett.todo_application.utils

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.leedsbeckett.todo_application.R

/**
 * It represents a Item touch helper which will manage the calls
 * to perform action such as swipe and drag and drop with custom options
 * @see ItemTouchHelper
 */
abstract class CustomItemTouchHelper(context: Context) :
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

    // Changing the colour to highlight the selected item
    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            viewHolder?.itemView?.setBackgroundColor(Color.LTGRAY)
        }
    }

    // Resetting the colour to the original state after swipe
    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.context, R.color.white))
    }
}