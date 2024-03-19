package com.example.mobdeve.s13.payao.malcolm.fitme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView

class WorkoutAdapter(private val data: ArrayList<Workout>) : RecyclerView.Adapter<WorkoutViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.workout_template,parent,false)

        return WorkoutViewHolder(view)
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val workout: Workout = data[position]
        holder.setWorkoutTitle(workout.workoutTitle)
        holder.setWorkoutDays(workout.workoutDays)


    }


}