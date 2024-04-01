package com.example.mobdeve.s13.payao.malcolm.fitme.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Stats
import com.example.mobdeve.s13.payao.malcolm.fitme.models.StatsViewHolder
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Exercise
//import com.example.mobdeve.s13.payao.malcolm.fitme.database.ExerciseDataHelper

/*
class StatsAdapter(private val data: List<Exercise>, private val context: Context) : RecyclerView.Adapter<StatsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.stats_template, parent, false)
        return StatsViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        val exercise: Exercise = data[position]
        holder.bind(exercise, data)
    }
}
*/
class StatsAdapter(private var data: List<Exercise>, private val context: Context) : RecyclerView.Adapter<StatsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.stats_template, parent, false)
        return StatsViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        val exercise: Exercise = data[position]
        holder.bind(exercise)
    }


    fun setData(newData: List<Exercise>) {
        data = newData
        notifyDataSetChanged()
    }
}





