package com.leedsbeckett.todo_application.model

data class Task(var name: String, var status: Int) {
    var id: String = ""
    var details: String = ""
    constructor() : this ("", 0)
}