package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Exercise
//import com.example.mobdeve.s13.payao.malcolm.fitme.database.ExerciseDataHelper


/*
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
*/
/*
class StatsViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {

    private val sessionTextView: TextView = itemView.findViewById(R.id.numberSession)
    private val repsTextView: TextView = itemView.findViewById(R.id.numberReps)
    private val kgTextView: TextView = itemView.findViewById(R.id.numberKG)


    fun bind(exercise: Exercise, exercises: List<Exercise>) {
        sessionTextView.text = exercise.sets.toString()
        repsTextView.text = exercise.reps.toString()
        kgTextView.text = exercise.KG.toString()


    }


}
*/


class StatsViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {

  //  private val exerciseTitleTextView: TextView = itemView.findViewById(R.id.exerciseTitleTV)
    private val repsTextView: TextView = itemView.findViewById(R.id.numberReps)
    private val setsTextView: TextView = itemView.findViewById(R.id.numberSession)
    private val kgTextView: TextView = itemView.findViewById(R.id.numberKG)
 //   private val instructionTextView: TextView = itemView.findViewById(R.id.instructionTV)

    fun bind(exercise: Exercise) {
 //       exerciseTitleTextView.text = exercise.exerciseTitle
        repsTextView.text = exercise.reps.toString()
        setsTextView.text = exercise.sets.toString()
        kgTextView.text = exercise.KG.toString()
//        instructionTextView.text = exercise.instruction
    }
}






















