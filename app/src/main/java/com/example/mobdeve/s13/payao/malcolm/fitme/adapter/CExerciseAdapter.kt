package com.example.mobdeve.s13.payao.malcolm.fitme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.models.CExerciseViewHolder
import com.example.mobdeve.s13.payao.malcolm.fitme.models.C_exercise
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Circuit


class CExerciseAdapter(private val data: List<C_exercise>, private val index:Int):RecyclerView.Adapter<CExerciseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CExerciseViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.circuit_template,parent,false)
        return CExerciseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CExerciseViewHolder, position: Int) {
        val cExercise:C_exercise = data[position]
        holder.bind(cExercise,index)

    }

}


/*
class CExerciseAdapter(private var data: List<Circuit>, private val index: Int) : RecyclerView.Adapter<CExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CExerciseViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.circuit_template, parent, false)
        return CExerciseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CExerciseViewHolder, position: Int) {
        val circuit: Circuit = data[position]
        holder.bind(circuit.ExerciseTitle)
    }

    // Method to update exercise titles
    fun updateExerciseTitles(exerciseTitles: List<String>) {
        // Convert exercise titles to Circuit objects
        val circuits = exerciseTitles.map { Circuit(it) }
        // Update adapter data
        data = circuits
        // Notify adapter of data change
        notifyDataSetChanged()
    }
}
*/