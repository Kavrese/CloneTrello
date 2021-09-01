package com.example.clonetrello

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AdapterDatesSingle(val listDate: List<ModelCardDate>, val onNewInterface: OnNewInterface): RecyclerView.Adapter<AdapterDatesSingle.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date_card = itemView.findViewById<TextView>(R.id.date_card)
        val days_card = itemView.findViewById<TextView>(R.id.days_card)
        val count_task_card = itemView.findViewById<TextView>(R.id.count_task_card)
        val count_worker_card = itemView.findViewById<TextView>(R.id.count_worker_card)
        val rec_task_card = itemView.findViewById<RecyclerView>(R.id.rec_task_card)
        val scroll = itemView.findViewById<NestedScrollView>(R.id.scroll)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_date_single_mode, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val count_day = countDays(listDate[position].date)

        val listWorker: Set<String> = listDate[position].list_task.map { elem -> elem.from }.toSet()
        val listComplete = listDate[position].list_task.filter { it.complete }

        holder.date_card.text = listDate[position].date
        holder.days_card.text = "${if (count_day < 0) "Прошло" else ""} ${Math.abs(count_day)} дня(ей)"
        holder.count_task_card.text = "Задач: ${listComplete.size}/${listDate[position].list_task.size}"
        holder.count_worker_card.text = "Исполнителей: ${listWorker.size}"

        val layoutManagerTask = LinearLayoutManager(holder.itemView.context)

        holder.rec_task_card.apply{
            adapter = AdapterTask(listDate[position].list_task)
            layoutManager = layoutManagerTask
        }

        onNewInterface.new_interface(object: OnNewPosition{
            override fun position(pos: Int) {
                holder.scroll.scrollTo(0, 0)
            }
        })
    }

    override fun getItemCount(): Int = listDate.size
}