package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.StatsAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.example.mobdeve.s13.payao.malcolm.fitme.database.ExerciseDataHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.w3c.dom.Text
import android.widget.Button
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


/*
class Stats : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_history_view_exercise)

        // Retrieve the exercise title from the intent extras
        val exerciseTitle = intent.getStringExtra("exerciseTitle")
        val instruction = intent.getStringExtra("instruction")



        // Find the TextView with id exerciseTV and set its text to the exercise title
        val exerciseTextView = findViewById<TextView>(R.id.exerciseTV)
        exerciseTextView.text = exerciseTitle

        // Retrieve exercise data from ExerciseDataHelper
        val exerciseList = ExerciseDataHelper.initializeExercises()

        // Find the totalKG TextView
        val totalKGTextView: TextView = findViewById(R.id.totalKG)

        // Find the totalReps TextView
        val totalRepsTextView: TextView = findViewById(R.id.totalReps)

        val totalSessionTextView: TextView = findViewById(R.id.totalSession)

        // Set up your RecyclerView and StatsAdapter, passing totalKGTextView
        val statsAdapter = StatsAdapter(exerciseList, this, totalKGTextView, totalRepsTextView, totalSessionTextView)
        val recyclerView: RecyclerView = findViewById(R.id.statsrecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = statsAdapter

        // Find the instructionsBtn button
        val instructionsBtn: Button = findViewById(R.id.instructionsBtn)

        // Set OnClickListener to the instructionsBtn button
        instructionsBtn.setOnClickListener {
            // Launch the InstructionsActivity with exercise title as an extra
            val intent = Intent(this, Instructions::class.java)
            intent.putExtra("exerciseTitle", exerciseTitle )
            intent.putExtra("instruction", instruction)
            startActivity(intent)
        }

        val backBtn: Button = findViewById(R.id.backBtn)

        backBtn.setOnClickListener{
            finish()
        }
    }
}
*/

class Stats : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_history_view_exercise)

        // Retrieve the exercise title from the intent extras
        val intentExerciseTitle = intent.getStringExtra("exerciseTitle")
        val intentInstruction = intent.getStringExtra("instruction")

        // Find the TextView with id exerciseTV and set its text to the exercise title
        val exerciseTextView = findViewById<TextView>(R.id.exerciseTV)
        exerciseTextView.text = intentExerciseTitle

        // Fetch exercise data
        fetchExerciseData(intentExerciseTitle)

        // Set up RecyclerView
        setupRecyclerView()

        // Find the instructionsBtn button
        val instructionsBtn: Button = findViewById(R.id.instructionsBtn)

        // Set OnClickListener to the instructionsBtn button
        instructionsBtn.setOnClickListener {
            // Launch the InstructionsActivity with exercise title as an extra
            val intent = Intent(this, Instructions::class.java)
            intent.putExtra("exerciseTitle", intentExerciseTitle)
            intent.putExtra("instruction", intentInstruction)
            startActivity(intent)
        }

        val backBtn: Button = findViewById(R.id.backBtn)

        backBtn.setOnClickListener{
            finish()
        }
    }

    private fun setupRecyclerView() {
        val statsAdapter = StatsAdapter(ArrayList(), this)
        val recyclerView: RecyclerView = findViewById(R.id.statsrecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = statsAdapter
    }

    private fun fetchExerciseData(exerciseTitle: String?) {
        if (exerciseTitle == null) return

        // Initialize Firestore instance
        val db = FirebaseFirestore.getInstance()

        // Get current user's UID
        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid

        if (uid != null) {
            // Query Firestore to retrieve exercise data for the current user
            db.collection("userExercise").document(uid).collection("listOfPastExercise")
                .whereEqualTo("exTitle", exerciseTitle)
                .get()
                .addOnSuccessListener { documents ->
                    val exercises = ArrayList<Exercise>()
                    for (document in documents) {
                        val exTitle = document.getString("exTitle") ?: ""
                        val reps = document.getLong("Reps")?.toInt() ?: 0
                        val sets = document.getLong("Sets")?.toInt() ?: 0
                        val kg = document.getLong("Kg")?.toInt() ?: 0
                        val instruction = document.getString("instruction") ?: ""

                        val exercise = Exercise(exTitle, reps, sets, kg, instruction)
                        exercises.add(exercise)
                    }

                    // Handle fetched exercises here (e.g., update UI)
                    val recyclerView: RecyclerView = findViewById(R.id.statsrecyclerView)
                    val statsAdapter = recyclerView.adapter as? StatsAdapter
                    statsAdapter?.setData(exercises)
                }
                .addOnFailureListener { exception ->
                    // Handle error gracefully
                }
        }
    }
}










