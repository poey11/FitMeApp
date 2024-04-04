package com.example.mobdeve.s13.payao.malcolm.fitme.activities

import android.annotation.SuppressLint
import android.content.Intent
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

        val addnewBtn = findViewById<Button>(R.id.addBtn)
        recyclerView = findViewById(R.id.circuitRecyclerView)
        workoutTitle = findViewById(R.id.textView2)
        val currentWorkout = intent.getSerializableExtra("currentWorkout") as? Workout
        val currentWorkoutID= intent.getStringExtra("clickedWorkoutID")

        // Retrieve the clicked workout title from the intent extras
        val clickedWorkoutTitle = intent.getStringExtra("clickedWorkoutTitle")


        if (currentWorkout != null) {
            workoutTitle.text = currentWorkout.workoutTitle
            fetchListOfExercises(currentWorkout.workoutID)
        }


        if (!clickedWorkoutTitle.isNullOrEmpty()) {
            workoutTitle.text = clickedWorkoutTitle
            if (currentWorkoutID != null) {
                fetchListOfExercises(currentWorkoutID)
                Log.e("JUSTINEEEE", currentWorkoutID)
            } else {
                Log.e("JUSTINEEEE", "Current Workout ID is null")
            }
        }

        val backBtn: Button = findViewById(R.id.button4)

        backBtn.setOnClickListener {
            finish()
        }
        addnewBtn.setOnClickListener{
            val intent = Intent(this, CircuitExList::class.java)
            if (currentWorkout != null) {
                intent.putExtra("workoutID", currentWorkout.workoutID)
            }else{
                intent.putExtra("workoutID", currentWorkoutID)
            }
            startActivity(intent)
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

