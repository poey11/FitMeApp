package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat.startActivity
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
        val mainLayout :LinearLayout = itemView.findViewById(R.id.linearLayout2)
        tvExerciseTitle.text = exercise
        infoBtn.setOnClickListener {
            val intent = Intent(itemView.context, Instructions::class.java).apply {
                putExtra("exerciseTitle", exercise)
            }
            itemView.context.startActivity(intent)
        }
        incBtn.setOnClickListener {
            val newLinearLayout =  LinearLayout(itemView.context)
            newLinearLayout.orientation = LinearLayout.HORIZONTAL
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            newLinearLayout.layoutParams = params

            val setTv = TextView(itemView.context)
            val setTvParams = LinearLayout.LayoutParams(
                R.dimen.defWidth,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            setTv.text = nos.toString()
            setTv.textSize = R.dimen.defTextSize.toFloat()
            setTv.typeface = ResourcesCompat.getFont(itemView.context, R.font.josefin_sans_semibold)
            setTv.layoutParams = setTvParams


            nos++
            newLinearLayout.addView(setTv)
            mainLayout.addView(newLinearLayout)
        }
        decBtn.setOnClickListener {
            mainLayout.removeViewAt(mainLayout.childCount-1)
        }
    }

}
