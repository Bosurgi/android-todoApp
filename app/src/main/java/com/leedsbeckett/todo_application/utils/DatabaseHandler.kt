package com.leedsbeckett.todo_application.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.leedsbeckett.todo_application.model.Task

private const val NAME =            "Tasks.db"
private const val VERSION =         1
private const val COLUMN_ID =       "_id"
private const val COLUMN_TASK =     "task"
private const val COLUMN_STATUS =   "task_status"

private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE $NAME (" +
            "$COLUMN_ID INTEGER PRIMARY KEY," +
            "$COLUMN_TASK TEXT," +
            "$COLUMN_STATUS INTEGER)"

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $NAME"

/**
 * Handler for managing the database to add and remove data
 * full documentation at [Documentation Link](https://developer.android.com/training/data-storage/sqlite)
 */
class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    // Instantiating database helper
    private val dbHelper : DatabaseHandler = DatabaseHandler(context)


    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        // Dropping the previous table
        db?.execSQL(SQL_DELETE_ENTRIES)
        // Creating new table
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    /**
     * Adding task into the database using its values
     */
    fun addTask(task: Task) {
        // Set database into write mode
        val db = dbHelper.writableDatabase

        // New map of values with name and status as keys
        val values = ContentValues().apply {
            put(COLUMN_TASK, task.name)
            put(COLUMN_STATUS, task.isDone)
        }
        // Inserting the new values
        val newRowId = db?.insert(NAME, null, values)
    }

    /**
     * It reads all data from the database and store it into cursor
     * @return cursor with data
     */
    fun readAllData(): Cursor {
        // Setting database to readable
        val db = dbHelper.readableDatabase

        // Setting the query to interrogate all entries of the database
        val query: String = "SELECT * FROM $NAME"

        // Fetching all data from database with query
        return db.rawQuery(query, null)
    }
    /***
     * It gets all the tasks present in the database from cursor
     * @return A list of tasks to display
     */
    fun getTaskList(cursor: Cursor): List<Task> {

        // Instantiating the list of task
        val taskList: MutableList<Task> = mutableListOf()

        // Index starting from -1 - Looping through all the cursor's elements
        while(cursor.moveToNext()) {

            // Instantiating new Task
            val task: Task = Task()

            // Getting the data
            if(cursor.moveToFirst()){
                task.id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                task.isDone = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_STATUS))
                task.name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TASK))

                // Adding the task to the list
                taskList.add(task)
            }
        } // End of while

        // Closing the cursor
        cursor.close()

        return taskList

    } // End of method


} // End of class