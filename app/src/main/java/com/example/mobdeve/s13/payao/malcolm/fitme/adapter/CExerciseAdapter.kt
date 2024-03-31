package com.example.mobdeve.s13.payao.malcolm.fitme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.models.CExerciseViewHolder



class CExerciseAdapter(private val data: List<String>):RecyclerView.Adapter<CExerciseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CExerciseViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.circuit_template,parent,false)
        return CExerciseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CExerciseViewHolder, position: Int) {
        val cExercise: String = data[position]
        holder.bind(cExercise)
    }
    fun removeCExerciseItem(position: Int) {
        data.toMutableList().removeAt(position)
        notifyItemRemoved(position)
    }


}

