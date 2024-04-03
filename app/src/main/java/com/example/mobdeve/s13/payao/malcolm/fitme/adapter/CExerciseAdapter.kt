package com.example.mobdeve.s13.payao.malcolm.fitme.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.activities.ViewWorkout
import com.example.mobdeve.s13.payao.malcolm.fitme.models.CExerciseViewHolder


class CExerciseAdapter(private val data: List<String>,private val onViewHolderCreated: (holder: CExerciseViewHolder) -> Unit) : RecyclerView.Adapter<CExerciseViewHolder>() {

    private val dynamicViews = mutableListOf<View>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CExerciseViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.circuit_template, parent, false)
        val holder = CExerciseViewHolder(view)
        // Invoke the callback to notify ViewWorkout about ViewHolder creation
        onViewHolderCreated(holder)
        return holder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CExerciseViewHolder,  position: Int) {
        val cExercise: String = data[position]
        holder.bind(cExercise,dynamicViews)
    }

    fun getDynamicViews(): MutableList<View> {
        return dynamicViews
    }


}

