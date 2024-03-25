package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.StatsAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.database.ExerciseDataHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.w3c.dom.Text
import android.widget.Button
import android.content.Intent


/*
class Stats : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.fragment_history_view_exercise)

        // Retrieve the exercise title from the intent extras
        val exerciseTitle = intent.getStringExtra("exerciseTitle")

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
        val statsAdapter = StatsAdapter(exerciseList, this, totalKGTextView,totalRepsTextView, totalSessionTextView)


        val recyclerView: RecyclerView = findViewById(R.id.statsrecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = statsAdapter



    }
}

*/
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
    }
}







