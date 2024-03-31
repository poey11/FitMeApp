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
//import com.example.mobdeve.s13.payao.malcolm.fitme.models.C_exercise
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Circuit
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Workout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

/*
class ViewWorkout : AppCompatActivity() {

    private lateinit var workoutTitle: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var cExerciseAdapter: CExerciseAdapter


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_workout)

        recyclerView = findViewById(R.id.circuitRecyclerView)
        workoutTitle = findViewById(R.id.textView2)
        val currentWorkout = intent.getSerializableExtra("currentWorkout") as? Workout

        if (currentWorkout != null) {
            workoutTitle.text = currentWorkout.workoutTitle
            setupRecyclerView(currentWorkout.circuit)
        }

        val backBtn: Button = findViewById(R.id.button4)

        backBtn.setOnClickListener {
            finish()
        }
    }

    private fun setupRecyclerView(data: Array<Circuit>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        cExerciseAdapter = CExerciseAdapter(data.flatMap { it.setOfExercise }, index = 0)
        recyclerView.adapter = cExerciseAdapter
    }



}
 */

class ViewWorkout : AppCompatActivity() {

    private lateinit var workoutTitle: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseTitles: List<String> // List to hold exercise titles
    private lateinit var exerciseAdapter: CExerciseAdapter // Assuming CExerciseAdapter is the adapter for displaying exercises

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
    }

    private fun fetchListOfExercises(workoutTitle: String) {
        // Get the current user's UID from FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid

        // Check if UID is not null before proceeding
        uid?.let {
            // Query Firestore collection "userExercise/{UID}/listOfWorkouts/{WorkoutTitle}/listOfExercises"
            db.collection("userExercise").document(uid)
                .collection("listOfWorkouts").document(workoutTitle)
                .collection("listOfExercises")
                .get()
                .addOnSuccessListener { documents ->

                    val exerciseTitlesList = mutableListOf<String>()
                    for (document in documents) {
                        // Parse exercise data from Firestore document
                        val exerciseTitle = document.getString("ExerciseTitle")
                        if (exerciseTitle != null) {
                            exerciseTitlesList.add(exerciseTitle)
                        }


                    // Log the list of exercise titles
                 //   Log.d("retrieved exercises" "Exercise Titles List: ${documents})

                    }



                    // Update RecyclerView adapter with the retrieved list of exercise titles
                    updateRecyclerView(exerciseTitlesList)
                }
                .addOnFailureListener { exception ->
                    // Handle any errors
                    Log.e(TAG, "Error getting documents: ", exception)
                }
        }
    }

    private fun updateRecyclerView(exerciseTitles: List<String>) {
        this.exerciseTitles = exerciseTitles
        recyclerView.layoutManager = LinearLayoutManager(this)
        exerciseAdapter = CExerciseAdapter(exerciseTitles,0)
        recyclerView.adapter = exerciseAdapter
    }

    companion object {
        private const val TAG = "ViewWorkout"
    }
}

