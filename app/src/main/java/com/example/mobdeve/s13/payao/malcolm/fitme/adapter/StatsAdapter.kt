package com.example.mobdeve.s13.payao.malcolm.fitme.adapter



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Exercise
import com.example.mobdeve.s13.payao.malcolm.fitme.models.StatsViewHolder

class StatsAdapter(private var data: List<Exercise>, private val context: Context) : RecyclerView.Adapter<StatsViewHolder>() {

    private var totalSession: Int = 0
    private var highestReps: Int = 0
    private var highestKg: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.stats_template, parent, false)
        return StatsViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        val exercise: Exercise = data[position]
        holder.bind(exercise)
    }

    fun setData(newData: List<Exercise>) {
        data = newData
        calculateTotalValues()
        notifyDataSetChanged()
    }

    private fun calculateTotalValues() {
        totalSession = data.sumBy { it.sets }
        highestReps = data.maxByOrNull { it.reps }?.reps ?: 0
        highestKg = data.maxByOrNull { it.KG }?.KG ?: 0
    }
}






