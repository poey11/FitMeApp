package com.example.mobdeve.s13.payao.malcolm.fitme.models


import android.app.ActionBar.LayoutParams
import android.content.Intent
import android.util.Log
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
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlin.math.log

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
    private  var doneExercise: MutableList<DoneExercise> = mutableListOf()
    private  var dynamicViews: MutableList<View> = mutableListOf()
    private val donBtn : Button = itemView.findViewById(R.id.button7)
    private  var mainLayout :LinearLayout = itemView.findViewById(R.id.linearLayout2)
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private lateinit var workoutID: String
    private lateinit var exerciseID: String
    fun bind(exercise: String, workoutID: String,EID: String){
        var nos = 2
        this.workoutID = workoutID
        this.exerciseID = EID
        dynamicViews.clear()
        doneExercise.clear()
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

        donBtn.setOnClickListener{
            collectData()
            disableButtons()
        }

        decBtn.setOnClickListener {
           if(nos>2){
               nos--
               mainLayout.removeViewAt(mainLayout.childCount-1)
           }
        }


    }


    private fun collectData(){

        if(setCB.isChecked){
            doneExercise.add(DoneExercise(tvExerciseTitle.text.toString(), setET.text.toString().toInt(), setKET.text.toString().toFloat(), setTVWith.text.toString().toInt()))
        }
        for (layout in dynamicViews) {
            val checkBox = layout.findViewById<CheckBox>(R.id.checkBox)
            if (checkBox.isChecked) {
                val setTv = layout.findViewById<TextView>(R.id.setNosTV)
                val setRepET = layout.findViewById<EditText>(R.id.repsNosTV)
                val setKgET = layout.findViewById<EditText>(R.id.kgNosTV)

                val setNumber = setTv.text.toString().toInt()
                val repValue = setRepET.text.toString().toIntOrNull() ?: 0
                val kgValue = setKgET.text.toString().toFloatOrNull() ?: 0.0

                val doneExerciseItem = DoneExercise(tvExerciseTitle.text.toString(), repValue, kgValue.toFloat(), setNumber)
                doneExercise.add(doneExerciseItem)
            }


        }

        sendToDb()
    }

    private fun sendToDb(){
      val currentUser = auth.currentUser
       if(currentUser!=null){
           for (i in doneExercise){
               db.collection("userExercise").document(currentUser.uid)
                   .collection("listOfPastExercise").add(i).addOnSuccessListener {
                       Log.d("TAG", "DocumentSnapshot added with ID: ${it.id}")
                   }
           }


       }
    }
    private fun disableButtons(){
        setET.isEnabled = false
        setKET.isEnabled = false
        setCB.isEnabled = false

        for (layout in dynamicViews) {
            val checkBox = layout.findViewById<CheckBox>(R.id.checkBox)
            val repET = layout.findViewById<EditText>(R.id.repsNosTV)
            val kgET = layout.findViewById<EditText>(R.id.kgNosTV)
            repET.isEnabled = false
            kgET.isEnabled = false
            checkBox.isEnabled = false
        }

        incBtn.isEnabled = false
        donBtn.isEnabled = false
        decBtn.isEnabled = false
    }

    fun removeCExerciseItemAtDB() {
        Log.d("JUSTANNE", "Position: $adapterPosition")
        val currentUser = auth.currentUser
        if (currentUser != null) {
            db.collection("userExercise").document(currentUser.uid)
                .collection("listOfWorkouts").document(workoutID)
                .collection("listOfExercises").document(exerciseID).delete()
                .addOnSuccessListener {
                    Log.d("JUSTANNE", "DocumentSnapshot successfully deleted! $exerciseID")
                }
                .addOnFailureListener { e ->
                    Log.w("JUSTANNE", "Error deleting document", e)
                }
        }

    }

}
