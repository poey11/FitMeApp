package com.example.mobdeve.s13.payao.malcolm.fitme.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.CExerciseAdapter
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Circuit
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Workout


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

/*
class ViewWorkout : AppCompatActivity() {

    private lateinit var workoutTitle: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var cExerciseAdapter: CExerciseAdapter


    private fun setupRecyclerView(data: List<Circuit>) {
        recyclerView.layoutManager = LinearLayoutManager(this)

        cExerciseAdapter = CExerciseAdapter(data, index = 0)
        recyclerView.adapter = cExerciseAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_workout)

        recyclerView = findViewById(R.id.circuitRecyclerView)
        workoutTitle = findViewById(R.id.textView2)
        val currentWorkout = intent.getSerializableExtra("currentWorkout") as? Workout

        if (currentWorkout != null) {
            workoutTitle.text = currentWorkout.workoutTitle

            val circuitList = currentWorkout.circuit.toList()

            setupRecyclerView(circuitList)
        }

        val backBtn: Button = findViewById(R.id.button4)

        backBtn.setOnClickListener {
            finish()
        }
    }




}
 */