/*
package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Exercise





class StatsViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {


    private val repsTextView: TextView = itemView.findViewById(R.id.numberReps)
    private val setsTextView: TextView = itemView.findViewById(R.id.numberSession)
    private val kgTextView: TextView = itemView.findViewById(R.id.numberKG)


    fun bind(exercise: Exercise) {

        repsTextView.text = exercise.reps.toString()
        setsTextView.text = exercise.sets.toString()
        kgTextView.text = exercise.KG.toString()

    }
}
*/
package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Exercise

class StatsViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {

    private val repsTextView: TextView = itemView.findViewById(R.id.numberReps)
    private val setsTextView: TextView = itemView.findViewById(R.id.numberSession)
    private val kgTextView: TextView = itemView.findViewById(R.id.numberKG)

    fun bind(exercise: Exercise) {
        repsTextView.text = exercise.reps.toString()
        setsTextView.text = exercise.sets.toString()
        kgTextView.text = exercise.KG.toString()
    }

    fun bindTotalSession(totalSession: Int) {
        setsTextView.text = totalSession.toString()
    }

    fun bindTotalReps(highestReps: Int) {
        repsTextView.text = highestReps.toString()
    }

    fun bindTotalKG(highestKg: Int) {
        kgTextView.text = highestKg.toString()
    }
}






















