package com.example.mobdeve.s13.payao.malcolm.fitme.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.CExerciseAdapter
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Workout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ViewWorkout : AppCompatActivity() {

    private lateinit var workoutTitle: TextView
    private lateinit var recyclerView: RecyclerView
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
            finish()
        }
    }


    private lateinit var EID:String
    private fun fetchListOfExercises(workoutID: String) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid

        uid?.let {
            db.collection("userExercise").document(uid)
                .collection("listOfWorkouts").document(workoutID)
                .collection("listOfExercises")
                .get()
                .addOnSuccessListener { documents ->

                    val exerciseTitlesList: MutableList<String> = mutableListOf()
                    for (document in documents) {
                        val exerciseTitle = document.getString("ExerciseTitle")
                        if (exerciseTitle != null) {
                            exerciseTitlesList.add(exerciseTitle)
                            EID = document.id
                        }
                    }
                    updateRecyclerView(exerciseTitlesList, workoutID)
                }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "Error getting documents: ", exception)
                }
        }
    }

    private fun updateRecyclerView(exerciseTitles: MutableList<String>, workoutID: String) {

        recyclerView.layoutManager = LinearLayoutManager(this)
        exerciseAdapter = CExerciseAdapter(exerciseTitles, workoutID, EID)
        recyclerView.adapter = exerciseAdapter
    }

    companion object {
        private const val TAG = "ViewWorkout"
    }
}

