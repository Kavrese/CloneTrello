package com.example.clonetrello

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.Math.abs

class AdapterDatesGroup(val listDate: List<ModelCardDate>, val onClickListener: OnClickDateGroup): RecyclerView.Adapter<AdapterDatesGroup.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date_group = itemView.findViewById<TextView>(R.id.date_group)
        val days_group = itemView.findViewById<TextView>(R.id.days_group)
        val count_task_group = itemView.findViewById<TextView>(R.id.task_count_group)
        val count_worker_group = itemView.findViewById<TextView>(R.id.worker_count_group)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_date_group_mode, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val count_day = countDays(listDate[position].date)

        val listWorker: Set<String> = listDate[position].list_task.map { elem -> elem.from }.toSet()
        val listComplete = listDate[position].list_task.filter { it.complete }

        holder.date_group.text = listDate[position].date
        holder.days_group.text = "${if (count_day < 0) "Прошло" else ""} ${abs(count_day)} дня(ей)"
        holder.count_task_group.text = "Задач: ${listComplete.size}/${listDate[position].list_task.size}"
        holder.count_worker_group.text = "Исполнителей: ${listWorker.size}"

        holder.itemView.setOnClickListener {
            onClickListener.onClick(position)
        }
    }

    override fun getItemCount(): Int = listDate.size
}