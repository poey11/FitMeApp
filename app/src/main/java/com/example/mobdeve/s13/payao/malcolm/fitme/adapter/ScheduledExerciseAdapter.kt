package com.example.mobdeve.s13.payao.malcolm.fitme.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.models.ScheduledExerciseViewHolder
import com.example.mobdeve.s13.payao.malcolm.fitme.models.ScheduledExercise



class ScheduledExerciseAdapter(private val exercises: ArrayList<ScheduledExercise>, private val listener: OnWorkoutTitleClickListener) :
    RecyclerView.Adapter<ScheduledExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduledExerciseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_scheduled_exercise, parent, false)
        return ScheduledExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduledExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.bind(exercise)

        // Set click listener for the workout title
        holder.itemView.setOnClickListener {
            listener.onWorkoutTitleClick(exercise.workoutTitle, exercise.workoutID)
        }

    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    // Method to update the data of the adapter
    fun setData(newList: ArrayList<ScheduledExercise>) {
        exercises.clear() // Clear the existing list
        exercises.addAll(newList) // Add all items from the new list
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }

    interface OnWorkoutTitleClickListener {
        fun onWorkoutTitleClick(workoutTitle: String, workoutID: String)
    }

}
