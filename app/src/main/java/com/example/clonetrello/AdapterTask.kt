package com.example.clonetrello

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

class AdapterTask(val listTask: List<ModelTask>): RecyclerView.Adapter<AdapterTask.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title_task = itemView.findViewById<TextView>(R.id.title_task)
        val desc_task = itemView.findViewById<TextView>(R.id.desc_task)
        val for_task = itemView.findViewById<TextView>(R.id.for_task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title_task.text = listTask[position].title_task
        holder.desc_task.text = listTask[position].desk_task
        holder.for_task.text = listTask[position].from

        holder.itemView.alpha = if (listTask[position].complete) 0.5f  else 1f
    }

    override fun getItemCount(): Int = listTask.size
}