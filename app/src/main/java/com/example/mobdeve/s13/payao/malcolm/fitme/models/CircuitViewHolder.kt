package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.CExerciseAdapter

class CircuitViewHolder(view: View, private var context: Context): RecyclerView.ViewHolder(view) {
    private lateinit var currentCircuit: Circuit
    private lateinit var recyclerView: RecyclerView
    private lateinit var cExerciseAdapter : CExerciseAdapter
    private lateinit var linearLayout: LinearLayoutManager
    fun bind(circuit: Circuit,index:Int){
        currentCircuit = circuit
        recyclerView = itemView.findViewById(R.id.exerciseRecyclerView)
        recyclerSetup()
    }

    private fun recyclerSetup(){
        linearLayout = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayout
        cExerciseAdapter = CExerciseAdapter(currentCircuit.setOfExercise,adapterPosition)
        recyclerView.adapter = cExerciseAdapter
    }
}