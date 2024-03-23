package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Exercise
import com.example.mobdeve.s13.payao.malcolm.fitme.database.ExerciseDataHelper



class StatsViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {

    private val numberSession: TextView = itemView.findViewById(R.id.numberSession)
    private val numberReps: TextView = itemView.findViewById(R.id.numberReps)
    private val numberKG: TextView = itemView.findViewById(R.id.numberKG)



    fun bind(exercise: Exercise) {
        // Set the values for reps, kg, and sets based on the Exercise object
        numberSession.text = exercise.exerciseId.toString()
        numberReps.text = exercise.reps.toString()
        numberKG.text = exercise.KG.toString()

    }
}




















