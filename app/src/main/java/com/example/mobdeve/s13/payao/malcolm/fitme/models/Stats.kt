package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.StatsAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.database.ExerciseDataHelper


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


    }
}
 */


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

        // Set up your RecyclerView and StatsAdapter
        val statsAdapter = StatsAdapter(exerciseList, this)

        val recyclerView: RecyclerView = findViewById(R.id.statsrecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = statsAdapter
    }
}


