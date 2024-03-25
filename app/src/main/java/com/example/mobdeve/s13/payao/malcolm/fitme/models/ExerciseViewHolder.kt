package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.HistoryFragment

class ExerciseViewHolder(exerciseView: View, private val context: Context) : RecyclerView.ViewHolder(exerciseView) {

    private val exerciseTitle: TextView = exerciseView.findViewById(R.id.exerciseTitle)
    private lateinit var currentExercise: Exercise

    fun bind(exercise: Exercise) {
        currentExercise = exercise
        exerciseTitle.text = exercise.exerciseTitle
        itemView.setOnClickListener {
            val intent = Intent(context, Stats::class.java) // Replace StatsActivity with your actual activity name
            context.startActivity(intent)
        }

        itemView.setOnClickListener {
            val intent = Intent(context, Stats::class.java)
            intent.putExtra("exerciseTitle", currentExercise.exerciseTitle)
            intent.putExtra("instruction", currentExercise.instruction)
            context.startActivity(intent)
        }

    }



}
