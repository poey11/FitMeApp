package com.example.mobdeve.s13.payao.malcolm.fitme.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Exercise  // Assuming you have a model for Exercise
import com.example.mobdeve.s13.payao.malcolm.fitme.models.ExerciseViewHolder

class ExerciseAdapter(private val data: ArrayList<Exercise>, private val context: Context) : RecyclerView.Adapter<ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.history_template, parent, false)
        return ExerciseViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise: Exercise = data[position]
        holder.bind(exercise)

    }
}
