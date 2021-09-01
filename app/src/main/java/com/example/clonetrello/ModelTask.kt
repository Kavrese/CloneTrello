package com.example.clonetrello

data class ModelTask(
    val title_task: String,
    val desk_task: String,
    val from: String,
    val files: List<String>,
    val date: String,
    val complete: Boolean,
)
