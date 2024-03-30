package com.example.mobdeve.s13.payao.malcolm.fitme.models


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R

class ScheduledExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val exerciseNameTextView: TextView = itemView.findViewById(R.id.exerciseNameTextView)

    fun bind(exercise: ScheduledExercise) {
        exerciseNameTextView.text = exercise.name
    }
}