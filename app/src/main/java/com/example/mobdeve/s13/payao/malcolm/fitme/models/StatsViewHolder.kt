package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Exercise
import com.example.mobdeve.s13.payao.malcolm.fitme.database.ExerciseDataHelper


/*
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
*/
/*
class StatsViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {

    private val numberSession: TextView = itemView.findViewById(R.id.numberSession)
    private val numberReps: TextView = itemView.findViewById(R.id.numberReps)
    private val numberKG: TextView = itemView.findViewById(R.id.numberKG)
    private val totalKG: TextView = itemView.findViewById(R.id.totalKG)

    fun bind(exercise: Exercise, exerciseList: List<Exercise>) {
        // Set the values for reps, kg, and sets based on the Exercise object
        numberSession.text = exercise.exerciseId.toString()
        numberReps.text = exercise.reps.toString()
        numberKG.text = exercise.KG.toString()

        // Get the maximum KG from the exercise list using ExerciseDataHelper
        val maxKG = ExerciseDataHelper.getMaxKG(exerciseList)
        // Set the totalKG TextView to the maximum KG value
        totalKG.text = maxKG.toString()
    }
}
*/


class StatsViewHolder(itemView: View, private val context: Context, private val totalKGView: TextView,private val totalRepView: TextView, private val totalSessionView: TextView) : RecyclerView.ViewHolder(itemView) {

    private val sessionTextView: TextView = itemView.findViewById(R.id.numberSession)
    private val repsTextView: TextView = itemView.findViewById(R.id.numberReps)
    private val kgTextView: TextView = itemView.findViewById(R.id.numberKG)
    private val maxKGTextView: TextView = totalKGView
    private val maxRepsTextView: TextView = totalRepView
    private val maxSessionTextView: TextView = totalSessionView

    fun bind(exercise: Exercise, exercises: List<Exercise>) {
        sessionTextView.text = exercise.sets.toString()
        repsTextView.text = exercise.reps.toString()
        kgTextView.text = exercise.KG.toString()

        // Call getMaxKG function and set the value accordingly
        val maxKG = ExerciseDataHelper.getMaxKG(exercises)
        maxKGTextView.text = maxKG.toString()

        val maxRep = ExerciseDataHelper.getMaxReps(exercises)
        maxRepsTextView.text = maxRep.toString()

        val maxSession = ExerciseDataHelper.getMaxSession(exercises)
        maxSessionTextView.text = maxSession.toString()
    }


}
























