package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R

class ExerciseViewHolder(exerciseView: View, private val context: Context) : RecyclerView.ViewHolder(exerciseView) {

    private val exerciseTitle: TextView = exerciseView.findViewById(R.id.exerciseTitle)
    private lateinit var currentExercise: Exercise

    fun bind(exercise: Exercise) {
        currentExercise = exercise
        exerciseTitle.text = exercise.exerciseTitle
        itemView.setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                Toast.makeText(context, "${exerciseTitle.text} clicked at position: $position", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
