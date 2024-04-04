package com.example.mobdeve.s13.payao.malcolm.fitme.models


import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R



class CExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvExerciseTitle: TextView = itemView.findViewById(R.id.exerciseTitle)
    private  val infoBtn : Button = itemView.findViewById(R.id.infoBtn)
    private  val incBtn : Button = itemView.findViewById(R.id.increaseBtn)
    private  val decBtn : Button = itemView.findViewById(R.id.decreaseBtn)
    fun bind(exercise: String) {
        var nos = 2
        var mainLayout :LinearLayout = itemView.findViewById(R.id.linearLayout2)
        tvExerciseTitle.text = exercise
        infoBtn.setOnClickListener {
            val intent = Intent(itemView.context, Instructions::class.java).apply {
                putExtra("exerciseTitle", exercise)
            }
            itemView.context.startActivity(intent)
        }
        incBtn.setOnClickListener {
            // Create a new LinearLayout
            val newLinearLayout = LinearLayout(itemView.context)
            newLinearLayout.orientation = LinearLayout.HORIZONTAL
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            newLinearLayout.layoutParams = params

            val setTv = TextView(itemView.context)
            setTv.text = nos.toString()

            // Fetch actual text size from dimension resource
            val textSizePixels = itemView.context.resources.getDimensionPixelSize(R.dimen.defTextSize)
            setTv.textSize = textSizePixels.toFloat()

            setTv.typeface = ResourcesCompat.getFont(itemView.context, R.font.josefin_sans_semibold)

            val setTvParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT // Change to WRAP_CONTENT
            )

            setTv.layoutParams = setTvParams

            newLinearLayout.addView(setTv)

            // Find the mainLayout in the parent view
             mainLayout = itemView.findViewById<LinearLayout>(R.id.linearLayout2) // Change to your actual mainLayout ID
            mainLayout.addView(newLinearLayout)

            nos++
        }

        decBtn.setOnClickListener {
            nos--
            mainLayout.removeViewAt(mainLayout.childCount-1)
        }
    }

}
