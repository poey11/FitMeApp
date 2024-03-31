package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R



class CExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
   private val tvExerciseTitle: TextView = itemView.findViewById(R.id.exerciseTitle)

    fun bind(exercise: String) {
        tvExerciseTitle.text = exercise

    }
}
