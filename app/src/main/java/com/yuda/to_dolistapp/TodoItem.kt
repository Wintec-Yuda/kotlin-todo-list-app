package com.yuda.to_dolistapp

data class TodoItem(
    var id: Int,
    var task: String,
    var isDone: Boolean = false
)
