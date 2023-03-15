package com.leedsbeckett.todo_application.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.leedsbeckett.todo_application.model.Task

private const val DATABASE_NAME = "Tasks.db"
private const val TODO_TABLE = "todo_table"
private const val VERSION = 1
private const val COLUMN_ID = "_id"
private const val COLUMN_TASK = "task"
private const val COLUMN_STATUS = "task_status"

private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE $TODO_TABLE (" +
            "$COLUMN_ID INTEGER PRIMARY KEY," +
            "$COLUMN_TASK TEXT," +
            "$COLUMN_STATUS INTEGER)"

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TODO_TABLE"

/**
 * Handler for managing the database to add and remove data
 * full documentation at [Documentation Link](https://developer.android.com/training/data-storage/sqlite)
 */
class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        // Dropping the previous table
        db?.execSQL(SQL_DELETE_ENTRIES)
        // Creating new table
        onCreate(db)
    }

    fun openDatabase(){
        this.readableDatabase
    }

    /**
     * Adding task into the database using its values
     */
    fun addTask(task: Task) {

        // Set database into write mode
        val db = this.writableDatabase

        // New map of values with name and status as keys
        val values = ContentValues().apply {
            put(COLUMN_TASK, task.name)
            put(COLUMN_STATUS, task.isDone)
        }
        // Inserting the new values
        val newRowId = db?.insert(TODO_TABLE, null, values)
    }

    /**
     * It reads all data from the database and store it into cursor
     * @return cursor with data
     */
    fun readAllData(): Cursor {
        // Setting database to readable
        val db = this.readableDatabase

        // Setting the query to interrogate all entries of the database
        val query: String = "SELECT * FROM $TODO_TABLE"

        // Fetching all data from database with query
        return db.rawQuery(query, null)
    }

} // End of class