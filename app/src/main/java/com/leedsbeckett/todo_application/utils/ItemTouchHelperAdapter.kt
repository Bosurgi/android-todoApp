package com.leedsbeckett.todo_application.utils

/**
 * SAM - Single Abstract Method interface to support the Touch Helper
 */
interface ItemTouchHelperAdapter {

    /**
     * Listener which will perform an operation on an item
     * at position specified
     * @param position the position of the item in the array
     */
    fun onItemSwipe(position: Int)
}