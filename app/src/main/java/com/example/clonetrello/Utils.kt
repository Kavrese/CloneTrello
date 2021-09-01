package com.example.clonetrello

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun countDays(task_date: String): Int{
    val dateNow = Calendar.getInstance()
    val calendarDateTask = Calendar.getInstance()
    dateNow.time = Date()
    calendarDateTask.time = SimpleDateFormat("dd/MM/yy", Locale.getDefault()).parse(task_date)!!
    return TimeUnit.DAYS.convert(calendarDateTask.timeInMillis - dateNow.timeInMillis, TimeUnit.MILLISECONDS).toInt()
}