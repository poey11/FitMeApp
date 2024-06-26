package com.example.mobdeve.s13.payao.malcolm.fitme.models


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log



class Instructions : AppCompatActivity() {

    private lateinit var exerciseTitle: String
    private lateinit var instructionTextView: TextView

    private val TAG = "InstructionsActivity"

    // Firebase Firestore instance
    private val db = FirebaseFirestore.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instructions_history)

        // Retrieve the exercise title from the intent extras
        exerciseTitle = intent.getStringExtra("exerciseTitle") ?: ""
        instructionTextView = findViewById(R.id.instructionstepsTv)

        // Set the exercise title in the TextView
        val exerciseTextView = findViewById<TextView>(R.id.exerciseTV)
        exerciseTextView.text = exerciseTitle

        // Fetch and display the instructions
        fetchInstructions()

        val statsBtn: Button = findViewById(R.id.statsBtn)

        // Find the backBtn button
        val backBtn: Button = findViewById(R.id.backBtn)

        // Set OnClickListener to the backBtn button
        backBtn.setOnClickListener {
            // Finish the activity and go back to the previous one
            finish()
        }

        // Set OnClickListener to the backBtn button
        statsBtn.setOnClickListener {
            // Finish the activity and go back to the previous one
            finish()
        }
    }


    private fun fetchInstructions() {
        // Query the Firestore database for the instructions corresponding to the exercise title
        db.collection("exercises")
            .whereEqualTo("name", exerciseTitle)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val document = documents.first() // Assuming there's only one document with the given exercise title
                    // Retrieve the instructions from the document
                    val instructions = document.getString("instructions")
                    // Update the TextView with the instructions
                    instructionTextView.text = instructions ?: "Instructions not found"
                } else {
                    Log.d(TAG, "Document not found for exercise: $exerciseTitle")
                    instructionTextView.text = "Instructions not found"
                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors
                Log.e(TAG, "Failed to fetch instructions", exception)
                instructionTextView.text = "Failed to fetch instructions: ${exception.message}"
            }
    }

}

