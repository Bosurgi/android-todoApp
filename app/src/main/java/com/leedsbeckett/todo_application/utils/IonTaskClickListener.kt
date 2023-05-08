package com.leedsbeckett.todo_application.utils

import com.leedsbeckett.todo_application.model.Task

/**
 * Interface which represents a listener for tasks
 */
interface IonTaskClickListener {
    /**
     * Method which defines the listener when item is clicked
     * @param task the Item clicked
     */
    fun onTaskClicked(task: Task)
}