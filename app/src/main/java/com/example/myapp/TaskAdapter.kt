package com.example.myapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter (private val datos: ArrayList<Task>, val clickListener: (Task)-> Unit):
RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){
    class TaskViewHolder(val layout: View) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_list_item, parent, false) as LinearLayout
        return TaskViewHolder(layout)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = datos[position]
        val textViewCode: TextView = holder.layout.findViewById(R.id.textViewCode)
        val textViewType: TextView = holder.layout.findViewById(R.id.textViewType)
        //textViewTask.text = task.task
        textViewCode.text = task.code
        holder.layout.setOnClickListener { clickListener(task) }
    }

    override fun getItemCount(): Int {
        return datos.size
    }
}