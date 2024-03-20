package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R

class WorkoutViewHolder(workoutView: View, private val context:Context ): RecyclerView.ViewHolder(workoutView){

    private  val workoutTitle:TextView =  workoutView.findViewById(R.id.workoutTitle)
    private  val workoutDays:TextView =  workoutView.findViewById(R.id.workoutDates)
    private val workFrame:FrameLayout = workoutView.findViewById(R.id.workFrame)
    private lateinit var currentWorkout: Workout


    fun bind(workout: Workout){
        currentWorkout = workout
        workoutTitle.text = workout.workoutTitle
        workoutDays.text = workout.workoutDays.joinToString(" ")
        workFrame.setOnClickListener{
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                Toast.makeText(context,"${workoutTitle.text} clicked at position: $position", Toast.LENGTH_SHORT).show()
            }

        }

    }




}