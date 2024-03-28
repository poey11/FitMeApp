package com.example.mobdeve.s13.payao.malcolm.fitme.activities

/*
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

import com.example.mobdeve.s13.payao.malcolm.fitme.R

class CreateNewWorkout: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_new_workout)

        val backBtn:Button = findViewById(R.id.backBtn)

        backBtn.setOnClickListener{
            finish()
        }
    }


}*/

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.AddExerciseAdapter
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView


class CreateNewWorkout : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseAdapter: AddExerciseAdapter
    private lateinit var autoCompleteTextView: AutoCompleteTextView

    // Firebase Firestore instance
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_new_workout)

        val backBtn: Button = findViewById(R.id.backBtn)
        recyclerView = findViewById(R.id.recyclerView)

        backBtn.setOnClickListener {
            finish()
        }

        setupRecyclerView()
        fetchExercises()
        setupMuscleSpinner()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        exerciseAdapter = AddExerciseAdapter(emptyList())
        recyclerView.adapter = exerciseAdapter
    }

    private fun fetchExercises() {
        db.collection("exercises")
            .get()
            .addOnSuccessListener { result ->
                val exerciseNames = mutableListOf<String>()
                for (document in result) {
                    val name = document.getString("name")
                    name?.let { exerciseNames.add(it) }
                }
                exerciseAdapter.setData(exerciseNames)
            }
            .addOnFailureListener { exception ->
                // Handle errors
            }
    }


    private fun setupMuscleSpinner() {
        // Get the muscle array from strings.xml
        val muscleArray = resources.getStringArray(R.array.muscle)

        // Create an ArrayAdapter using the muscle array
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, muscleArray)

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the AutoCompleteTextView
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView2)
        autoCompleteTextView.setAdapter(adapter)

        // Set a listener to handle the selected item in the AutoCompleteTextView
        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            // Handle the selected muscle group
            val selectedMuscle = parent.getItemAtPosition(position).toString()
            // You can perform actions based on the selected muscle group here
        }
    }
}

