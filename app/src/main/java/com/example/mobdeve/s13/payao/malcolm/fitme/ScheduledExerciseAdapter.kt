package com.example.mobdeve.s13.payao.malcolm.fitme


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class ScheduledExerciseAdapter(private val exercises: ArrayList<ScheduledExercise>) :
    RecyclerView.Adapter<ScheduledExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduledExerciseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_scheduled_exercise, parent, false)
        return ScheduledExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduledExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.bind(exercise)
    }

    override fun getItemCount(): Int {
        return exercises.size
    }
}