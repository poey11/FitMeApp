package com.example.mobdeve.s13.payao.malcolm.fitme.adapter

import android.view.LayoutInflater
import android.view.View
import android.content.Intent
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Instructions

class AddExerciseAdapter(private var exercises: List<String>) :
    RecyclerView.Adapter<AddExerciseAdapter.ExerciseViewHolder>() {

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseName: TextView = itemView.findViewById(R.id.textView10)
        val infoButton: ImageButton = itemView.findViewById(R.id.infoBtn)

        init {
            infoButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val exercise = exercises[position]
                    val intent = Intent(itemView.context, Instructions::class.java).apply {
                        putExtra("exerciseTitle", exercise)
                    }
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.exercise_template, parent, false)
        return ExerciseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val currentItem = exercises[position]
        holder.exerciseName.text = currentItem
    }

    override fun getItemCount() = exercises.size

    fun setData(newData: List<String>) {
        exercises = newData
        notifyDataSetChanged()
    }
}
