package com.example.mobdeve.s13.payao.malcolm.fitme.adapter

import android.view.LayoutInflater
import android.view.View
import android.content.Intent
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.ImageButton
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Instructions


class AddExerciseAdapter(private var exercises: List<String>) :
    RecyclerView.Adapter<AddExerciseAdapter.ExerciseViewHolder>() {

    private var selectedExercises = mutableListOf<String>()

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseName: TextView = itemView.findViewById(R.id.textView10)
        val infoButton: ImageButton = itemView.findViewById(R.id.infoBtn)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)

        init {
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val exercise = exercises[position]
                    if (isChecked) {
                        selectedExercises.add(exercise)
                    } else {
                        selectedExercises.remove(exercise)
                    }
                }
            }
        }

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
        holder.checkBox.isChecked = selectedExercises.contains(currentItem)
    }

    override fun getItemCount() = exercises.size

    fun getSelectedExercises(): List<String> {
        return selectedExercises
    }

    fun setData(newData: List<String>) {
        exercises = newData
        notifyDataSetChanged()
    }
}
