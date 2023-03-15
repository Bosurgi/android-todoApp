package com.leedsbeckett.todo_application.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val NAME =            "Tasks.db"
private const val VERSION =         1
private const val COLUMN_ID =       "_id"
private const val COLUMN_TASK =     "task"
private const val COLUMN_STATUS =   "task_status"

class DatabaseHandler(
    context: Context?,
    name: String = NAME,
    version: Int = VERSION,
    openParams: SQLiteDatabase.OpenParams
) : SQLiteOpenHelper(context, name, version, openParams) {

    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


}