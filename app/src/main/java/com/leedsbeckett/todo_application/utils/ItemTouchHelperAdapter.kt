package com.leedsbeckett.todo_application.utils

/**
 * SAM - Single Abstract Method interface to support the Touch Helper
 */
fun interface ItemTouchHelperAdapter {

    fun onItemSwipe(position: Int)
}