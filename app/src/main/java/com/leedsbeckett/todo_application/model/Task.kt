package com.leedsbeckett.todo_application.model

/**
 * Data class for storing tasks
 * Changes - Since SQLite doesn't support Boolean, the status is represented by Integer
 *
 *  False = 0
 *  True = 1
 */
data class Task(var id: Int, var isDone: Int, var name: String) {

    // Empty constructor for Task with default values
    constructor() : this (-1, 0, "")
}