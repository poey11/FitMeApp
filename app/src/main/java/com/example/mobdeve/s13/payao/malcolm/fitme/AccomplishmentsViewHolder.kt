package com.example.mobdeve.s13.payao.malcolm.fitme

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class AccomplishmentViewHolder(accomplishmentView: View, private val context: Context) : RecyclerView.ViewHolder(accomplishmentView) {

    private val titleTextView: TextView = accomplishmentView.findViewById(R.id.titleTextView)

    fun bind(accomplishment: Accomplishment) {
        titleTextView.text = accomplishment.title

    }
}