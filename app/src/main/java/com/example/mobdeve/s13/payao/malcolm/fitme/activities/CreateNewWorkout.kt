package com.example.mobdeve.s13.payao.malcolm.fitme.activities

/*
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

class CreateNewWorkout : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseAdapter: AddExerciseAdapter

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
}

