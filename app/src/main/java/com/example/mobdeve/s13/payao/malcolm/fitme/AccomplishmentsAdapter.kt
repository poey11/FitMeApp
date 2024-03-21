package com.example.mobdeve.s13.payao.malcolm.fitme

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class AccomplishmentsAdapter(private val accomplishments: List<Accomplishment>) :
    RecyclerView.Adapter<AccomplishmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccomplishmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_accomplishment, parent, false)
        return AccomplishmentViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: AccomplishmentViewHolder, position: Int) {
        val accomplishment = accomplishments[position]
        holder.bind(accomplishment)
    }

    override fun getItemCount(): Int {
        return accomplishments.size
    }
}