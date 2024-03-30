package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.HistoryFragment
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.google.firebase.firestore.FirebaseFirestore
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.StatsAdapter
import com.example.mobdeve.s13.payao.malcolm.fitme.database.ExerciseDataHelper
import android.util.Log


/*
class Instructions: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.instructions_history)

        // Retrieve the exercise title from the intent extras
        val exerciseTitle = intent.getStringExtra("exerciseTitle")

        // Find the TextView with id exerciseTV and set its text to the exercise title
        val exerciseTextView = findViewById<TextView>(R.id.exerciseTV)
        exerciseTextView.text = exerciseTitle



        // Find the instructionsBtn button
        val statsBtn: Button = findViewById(R.id.statsBtn)

        // Set OnClickListener to the instructionsBtn button
        statsBtn.setOnClickListener {
            // Launch the InstructionsActivity with exercise title as an extra
            val intent = Intent(this, Stats::class.java)
            intent.putExtra("exerciseTitle", exerciseTitle)
            startActivity(intent)
        }


    }
}
*/
/*
class Instructions : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instructions_history)

        // Retrieve the exercise title and instructions from the intent extras
        val exerciseTitle = intent.getStringExtra("exerciseTitle")
        val instruction = intent.getStringExtra("instruction")

        // Find the TextViews with id exerciseTV and instructionStepsTv and set their text
        val exerciseTextView = findViewById<TextView>(R.id.exerciseTV)
        val instructionStepsTv = findViewById<TextView>(R.id.instructionstepsTv)

        exerciseTextView.text = exerciseTitle
        instructionStepsTv.text = instruction

        // Find the statsBtn button
        val statsBtn: Button = findViewById(R.id.statsBtn)

        // Set OnClickListener to the statsBtn button
        statsBtn.setOnClickListener {
            // Launch the Stats activity with exercise title as an extra
            val intent = Intent(this, Stats::class.java)
            intent.putExtra("exerciseTitle", exerciseTitle)
            startActivity(intent)
        }
    }
}

 */

/*
class Instructions : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instructions_history)

        // Retrieve the exercise title and instructions steps from the intent extras
        val exerciseTitle = intent.getStringExtra("exerciseTitle")
        val instructionsSteps = intent.getStringExtra("instruction")

        // Find the TextViews to display exercise title and instructions steps
        val exerciseTextView = findViewById<TextView>(R.id.exerciseTV)
        val instructionsStepsTextView = findViewById<TextView>(R.id.instructionstepsTv)

        // Set the text of TextViews
        exerciseTextView.text = exerciseTitle
        instructionsStepsTextView.text = instructionsSteps.toString()

        // Find the statsBtn button
        val statsBtn: Button = findViewById(R.id.statsBtn)

        // Set OnClickListener to the statsBtn button
        statsBtn.setOnClickListener {

            finish()
        }

        val backBtn: Button = findViewById(R.id.backBtn)
        backBtn.setOnClickListener{
            finish()
        }

    }
}*/


class Instructions : AppCompatActivity() {

    private lateinit var exerciseTitle: String
    private lateinit var instructionTextView: TextView

    private val TAG = "InstructionsActivity"

    // Firebase Firestore instance
    private val db = FirebaseFirestore.getInstance()

    /*override fun onCreate(savedInstanceState: Bundle?) {
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
    }*/

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

        // Find the backBtn button
        val backBtn: Button = findViewById(R.id.backBtn)

        // Set OnClickListener to the backBtn button
        backBtn.setOnClickListener {
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

