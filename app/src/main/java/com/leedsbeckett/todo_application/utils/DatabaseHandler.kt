package com.leedsbeckett.todo_application.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.leedsbeckett.todo_application.model.Task
import java.sql.RowId

private const val NAME =            "Tasks.db"
private const val VERSION =         1
private const val COLUMN_ID =       "_id"
private const val COLUMN_TASK =     "task"
private const val COLUMN_STATUS =   "task_status"

private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE $NAME (" +
            "$COLUMN_ID INTEGER PRIMARY KEY," +
            "$COLUMN_TASK TEXT," +
            "$COLUMN_STATUS BOOLEAN)"

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

    /***
     * It gets all the tasks present in the database
     * @return A list of tasks to display
     */
    fun getTaskList(): List<Task> {
        // Setting database to readable
        val db = dbHelper.readableDatabase

        // Sorting order ascendant
        val sortingOrder = "$NAME ASC"

        // Setting the query to interrogate all entries of the database
        val query: String = "SELECT * FROM $NAME"

        // Instantiating the list of task
        val taskList: MutableList<Task> = mutableListOf()

        // TODO: Adding the content to List of Tasks
        val cursor = db.rawQuery(query, null)

        return taskList
    }


} // End of class