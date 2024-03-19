package com.example.mobdeve.s13.payao.malcolm.fitme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WorkoutViewHolder(workoutView: View): RecyclerView.ViewHolder(workoutView){

    private  val workoutTitle:TextView =  workoutView.findViewById(R.id.workoutTitle)
    private  val workoutDays:TextView =  workoutView.findViewById(R.id.workoutDates)


    fun setWorkoutTitle(title:String){
        workoutTitle.text = title

    }

    fun setWorkoutDays(days:Array<String>){
        workoutDays.text = days.joinToString(" ")
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder{
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.workout_template, parent, false)
        return WorkoutViewHolder(view)
    }
}