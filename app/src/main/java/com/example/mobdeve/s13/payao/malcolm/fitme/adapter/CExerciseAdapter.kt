package com.example.mobdeve.s13.payao.malcolm.fitme.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.models.CExerciseViewHolder


class CExerciseAdapter(private val data: MutableList<String>, private val workoutID:String, private val eID: MutableList<String>) : RecyclerView.Adapter<CExerciseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CExerciseViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.circuit_template, parent, false)
        return CExerciseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CExerciseViewHolder,  position: Int) {
        val cExercise: String = data[position]
        val eID: String = eID[position]
        holder.bind(cExercise,workoutID, eID)
        holder.itemView.findViewById<Button>(R.id.delBtn).setOnClickListener{
            removeCExerciseItem(position)
            holder.removeCExerciseItemAtDB()
        }
    }

   private fun removeCExerciseItem(position: Int) {
       data.removeAt(position)
       notifyItemRemoved(position)
       notifyItemRangeChanged(position, itemCount - position)
   }




}

