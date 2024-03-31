package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
/*
class CExerciseViewHolder(cExerciseView:View):RecyclerView.ViewHolder(cExerciseView) {
    private  lateinit var currentExercise: C_exercise
    private  val exerciseTitle: TextView = cExerciseView.findViewById(R.id.textView4)
 //   private val exerciseDesc: TextView = cExerciseView.findViewById(R.id.textView6)
    private val exerciseNos:TextView = cExerciseView.findViewById(R.id.textView5)
    @SuppressLint("SetTextI18n")
    fun bind(cExercise: C_exercise, position:Int){
        val index =  adapterPosition+1
        exerciseNos.text = "${position+1}.${index}"
        currentExercise = cExercise
        exerciseTitle.text = currentExercise.ExerciseTitle
//        exerciseDesc.text = "${currentExercise.Sets} x ${currentExercise.Reps}"

    }

}
 */


class CExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  //  private val tvExerciseTitle: TextView = itemView.findViewById(R.id.exerciseTitle)
    private  val exerciseTitle: TextView = itemView.findViewById(R.id.textView4)
    fun bind(exercise: String) {
  //      tvExerciseTitle.text = exercise
        exerciseTitle.text = exercise
    }
}
