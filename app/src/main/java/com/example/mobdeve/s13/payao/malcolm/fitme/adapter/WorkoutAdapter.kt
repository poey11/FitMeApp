package com.example.mobdeve.s13.payao.malcolm.fitme.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity

import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.activities.ViewWorkout
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Workout
import com.example.mobdeve.s13.payao.malcolm.fitme.models.WorkoutViewHolder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

interface WorkoutItemClickListener {
    fun onDeleteClicked(position: Int)
}

class WorkoutAdapter(private val data: ArrayList<Workout>, private val context: Context) : RecyclerView.Adapter<WorkoutViewHolder>() {

    private lateinit var listener: WorkoutItemClickListener

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
        holder.itemView.findViewById<Button>(R.id.deleteBtn).setOnClickListener {
            listener.onDeleteClicked(position)
        }
    }

    fun setWorkoutItemClickListener(listener: WorkoutItemClickListener) {
        this.listener = listener
    }

    fun removeWorkoutItem(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun openWorkoutItem(position: Int) {
        val currentWorkout: Workout = data[position]
        val intent = Intent(context,  ViewWorkout::class.java)
        intent.putExtra("currentWorkout", currentWorkout)
        context.startActivity(intent)
    }

    fun getWorkoutItem(position: Int): Workout {
        return data[position]
    }

    fun setData(newData: ArrayList<Workout>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }


}
