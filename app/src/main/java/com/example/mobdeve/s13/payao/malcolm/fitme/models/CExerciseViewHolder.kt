package com.example.mobdeve.s13.payao.malcolm.fitme.models


import android.app.ActionBar.LayoutParams
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R

class CExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private  val infoBtn : Button = itemView.findViewById(R.id.infoBtn)
    private  val incBtn : Button = itemView.findViewById(R.id.increaseBtn)
    private  val decBtn : Button = itemView.findViewById(R.id.decreaseBtn)
    private val  setMainLY: LinearLayout = itemView.findViewById(R.id.mainLayoutInside)
    private val tvExerciseTitle: TextView = itemView.findViewById(R.id.exerciseTitle)
    private val setTVWith : TextView = itemView.findViewById(R.id.setNosTV)
    private val setET :EditText = itemView.findViewById(R.id.repsNosTV)
    private val setKET :EditText = itemView.findViewById(R.id.kgNosTV)
    private val setCB : CheckBox = itemView.findViewById(R.id.checkBox)



    fun bind(exercise: String, dynamicViews: MutableList<View>){
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
            val newLinearLayout = LinearLayout(itemView.context)
            newLinearLayout.layoutParams = setMainLY.layoutParams
            newLinearLayout.gravity = setMainLY.gravity

            val setDefTextSize = itemView.context.resources.getDimensionPixelSize(R.dimen.defTextSize)
            val setTv = TextView(itemView.context)
            setTv.id = setTVWith.id
            setTv.text = nos.toString()
            setTv.textSize = setDefTextSize.toFloat()
            setTv.gravity = setTVWith.gravity
            setTv.typeface = setTVWith.typeface
            setTv.layoutParams = setTVWith.layoutParams

            val setRepET = AppCompatEditText(itemView.context)
            val repParams = LayoutParams(setET.layoutParams.width, setET.layoutParams.height)
            val right = itemView.context.resources.getDimensionPixelSize(R.dimen.defRight)
            repParams.rightMargin =right
            setRepET.id = setET.id
            setRepET.hint = setET.hint
            setRepET.inputType = setET.inputType
            setRepET.textSize = setDefTextSize.toFloat()
            setRepET.gravity = setET.gravity
            setRepET.typeface = setET.typeface
            setRepET.layoutParams =  repParams

            val setKgET = AppCompatEditText(itemView.context)
            val setParams = LayoutParams(setET.layoutParams.width, setET.layoutParams.height)
            val setRight = itemView.context.resources.getDimensionPixelSize(R.dimen.setDefRight)
            setKgET.id = setKET.id
            setParams.rightMargin =setRight
            setKgET.hint = setET.hint
            setKgET.inputType = setKET.inputType
            setKgET.gravity =setET.gravity
            setKgET.textSize = setDefTextSize.toFloat()
            setKgET.typeface =setET.typeface
            setKgET.layoutParams =setParams

            val setCheckBox = AppCompatCheckBox(itemView.context)
            setCheckBox.layoutParams =setCB.layoutParams
            setCheckBox.id = setCB.id
            newLinearLayout.addView(setTv)
            newLinearLayout.addView(setRepET)
            newLinearLayout.addView(setKgET)
            newLinearLayout.addView(setCheckBox)

            mainLayout = itemView.findViewById<LinearLayout>(R.id.linearLayout2)
            dynamicViews.add(newLinearLayout)
            mainLayout.addView(newLinearLayout)
            nos++
        }

        decBtn.setOnClickListener {
           if(nos>2){
               nos--
               mainLayout.removeViewAt(mainLayout.childCount-1)
           }
        }

    }

    fun getExerciseTitle(): String {
        return tvExerciseTitle.text.toString()
    }


}
