package com.example.mobdeve.s13.payao.malcolm.fitme.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity

import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.activities.ViewWorkout
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Workout
import com.example.mobdeve.s13.payao.malcolm.fitme.models.WorkoutViewHolder

class WorkoutAdapter(private val data: ArrayList<Workout>, private val context: Context) : RecyclerView.Adapter<WorkoutViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.workout_template, parent, false)
        return WorkoutViewHolder(view,this)
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val workout: Workout = data[position]
        holder.bind(workout)
    }

    fun removeWorkoutItem(position: Int){
        data.removeAt(position)
        notifyItemRemoved(position)
    }

     fun openWorkoutItem(position:Int){
         val currentWorkout:Workout = data[position]
         val intent = Intent(context,  ViewWorkout::class.java)
         intent.putExtra("currentWorkout",currentWorkout)
         context.startActivity(intent)
    }
}
