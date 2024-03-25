package com.example.mobdeve.s13.payao.malcolm.fitme.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Circuit
import com.example.mobdeve.s13.payao.malcolm.fitme.models.CircuitViewHolder


class CircuitAdapter(private val data: Array<Circuit>, private var context: Context):RecyclerView.Adapter<CircuitViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CircuitViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.circuit_layout,parent,false)
        return CircuitViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return data.size
    }


    override fun onBindViewHolder(holder: CircuitViewHolder, position: Int) {
        val circuit: Circuit = data[position]
        holder.bind(circuit,position)
    }
}