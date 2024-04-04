package com.example.mobdeve.s13.payao.malcolm.fitme.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.CExerciseAdapter
import com.example.mobdeve.s13.payao.malcolm.fitme.models.CExerciseViewHolder
import com.example.mobdeve.s13.payao.malcolm.fitme.models.DoneExercise
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Workout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ViewWorkout : AppCompatActivity() {

    private lateinit var exerciseViewHolder: CExerciseViewHolder
    private lateinit var workoutTitle: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseTitles: List<String>
    private lateinit var exerciseAdapter: CExerciseAdapter
    private val db = FirebaseFirestore.getInstance()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_workout)

        recyclerView = findViewById(R.id.circuitRecyclerView)
        workoutTitle = findViewById(R.id.textView2)
        val currentWorkout = intent.getSerializableExtra("currentWorkout") as? Workout

        if (currentWorkout != null) {
            workoutTitle.text = currentWorkout.workoutTitle
            fetchListOfExercises(currentWorkout.workoutID)
        }

        val backBtn: Button = findViewById(R.id.button4)

        backBtn.setOnClickListener {
            finish()
        }


        val doneBtn: Button = findViewById(R.id.doneBtn)
        doneBtn.setOnClickListener {
            handleCheckedItems()
            finish()
        }
    }
    private lateinit var title : String
    private fun handleCheckedItems() {
        val dynamicViews = exerciseAdapter.getDynamicViews()
        val listOfDoneExercise = mutableListOf<DoneExercise>()
        for (i in 0 until exerciseAdapter.itemCount) {
            val exerciseViewHolder = recyclerView.findViewHolderForAdapterPosition(i) as? CExerciseViewHolder
            exerciseViewHolder?.let { viewHolder ->
                val cb = viewHolder.itemView.findViewById<CheckBox>(R.id.checkBox)
                if (cb.isChecked) {
                    title = viewHolder.itemView.findViewById<TextView>(R.id.exerciseTitle).text.toString()
                    val setted = viewHolder.itemView.findViewById<TextView>(R.id.setNosTV)
                    val repped = viewHolder.itemView.findViewById<EditText>(R.id.repsNosTV)
                    val kged = viewHolder.itemView.findViewById<EditText>(R.id.kgNosTV)
                    listOfDoneExercise.add(DoneExercise(title, repped.text.toString().toInt(), kged.text.toString().toFloat(), setted.text.toString().toInt()))
                }
            }
            for (view in dynamicViews) {
                val cb2 = view.findViewById<CheckBox>(R.id.checkBox)
                if (cb2.isChecked) {
                    val set = view.findViewById<TextView>(R.id.setNosTV)
                    val reps = view.findViewById<EditText>(R.id.repsNosTV)
                    val kg = view.findViewById<EditText>(R.id.kgNosTV)
                    listOfDoneExercise.add(DoneExercise(title, reps.text.toString().toInt(), kg.text.toString().toFloat(), set.text.toString().toInt()))
                }

            }

        }
        listOfDoneExercise.distinct().forEach {
            Log.d("WorkingTest", it.toString())
        }
    }

    private  fun sendTOFB(listOfEx: List<DoneExercise>){

    }



    private fun fetchListOfExercises(workoutTitle: String) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid

        uid?.let {
            db.collection("userExercise").document(uid)
                .collection("listOfWorkouts").document(workoutTitle)
                .collection("listOfExercises")
                .get()
                .addOnSuccessListener { documents ->

                    val exerciseTitlesList = mutableListOf<String>()
                    for (document in documents) {
                        val exerciseTitle = document.getString("ExerciseTitle")
                        if (exerciseTitle != null) {
                            exerciseTitlesList.add(exerciseTitle)
                        }
                    }
                    updateRecyclerView(exerciseTitlesList)
                }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "Error getting documents: ", exception)
                }
        }
    }

    private fun updateRecyclerView(exerciseTitles: List<String>) {
        this.exerciseTitles = exerciseTitles
        recyclerView.layoutManager = LinearLayoutManager(this)
        exerciseAdapter = CExerciseAdapter(exerciseTitles) { holder ->
            // Assign the reference to exerciseViewHolder when creating ViewHolder
            exerciseViewHolder = holder
        }
        recyclerView.adapter = exerciseAdapter
    }

    companion object {
        private const val TAG = "ViewWorkout"
    }
}

