package com.example.mobdeve.s13.payao.malcolm.fitme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.core.content.ContextCompat
import android.graphics.Color
import com.example.mobdeve.s13.payao.malcolm.fitme.models.CalendarViewHolder
import java.util.*



class CalendarAdapter(
    private val daysOfMonth: ArrayList<String>,
    private val onItemListener: OnItemListener,
    private val currentMonth: Int,
    private val currentYear: Int
) : RecyclerView.Adapter<CalendarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.calendar_cell, parent, false)
        val layoutParams = view.layoutParams
        layoutParams.height = (parent.height * 0.166666666).toInt()
        return CalendarViewHolder(view, onItemListener)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val dayOfMonth = daysOfMonth[position]
        holder.dayOfMonth.text = dayOfMonth

        // Get current date
        val calendar = Calendar.getInstance()
        val today = calendar.get(Calendar.DAY_OF_MONTH).toString()
        val currentMonthFromCalendar = calendar.get(Calendar.MONTH)
        val currentYearFromCalendar = calendar.get(Calendar.YEAR)

        // Shade the cell if it represents the current date of the current month and year
        if (dayOfMonth == today && currentMonth == currentMonthFromCalendar && currentYear == currentYearFromCalendar) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.purple))
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT)
        }

        holder.itemView.setOnClickListener {
            onItemListener.onItemClick(position, dayOfMonth)
        }
    }

    override fun getItemCount(): Int {
        return daysOfMonth.size
    }

    interface OnItemListener {
        fun onItemClick(position: Int, dayText: String?)
    }
}


