package com.example.myapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter2 (private val datos: ArrayList<Task>, val clickListener: (Task2)-> Unit):
RecyclerView.Adapter<TaskAdapter2.TaskViewHolder>(){
    class TaskViewHolder(val layout: View) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_list_item, parent, false) as LinearLayout
        return TaskViewHolder(layout)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task2 = datos[position]
        val textViewName: TextView = holder.layout.findViewById(R.id.textViewName)
        val textViewIdentification: TextView = holder.layout.findViewById(R.id.textViewCode)
        //textViewTask.text = task.task
        textViewName.text = task2.name
        holder.layout.setOnClickListener { clickListener(task2) }
    }

    private fun clickListener(task2: Task) {

    }

    override fun getItemCount(): Int {
        return datos.size
    }
}