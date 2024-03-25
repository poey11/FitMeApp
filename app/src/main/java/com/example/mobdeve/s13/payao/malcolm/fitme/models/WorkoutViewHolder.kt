package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.WorkoutAdapter
class WorkoutViewHolder(workoutView: View,private var workoutAdapter: WorkoutAdapter  ): RecyclerView.ViewHolder(workoutView){

    private  val workoutTitle:TextView =  workoutView.findViewById(R.id.workoutTitle)
    private  val workoutDays:TextView =  workoutView.findViewById(R.id.workoutDates)
    private val workFrame:FrameLayout = workoutView.findViewById(R.id.workFrame)
    private  val deleteBtn: Button = workoutView.findViewById(R.id.deleteBtn)
    private lateinit var currentWorkout: Workout

    init {
        workFrame.setOnClickListener{
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                workoutAdapter.openWorkoutItem(position)
            }
        }
        deleteBtn.setOnClickListener{
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                workoutAdapter.removeWorkoutItem(position)
            }
        }
    }
    fun bind(workout: Workout){
        currentWorkout = workout
        workoutTitle.text = workout.workoutTitle
        workoutDays.text = workout.workoutDays.joinToString(" ")

    }




}