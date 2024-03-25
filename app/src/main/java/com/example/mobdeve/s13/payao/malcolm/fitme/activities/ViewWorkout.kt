package com.example.mobdeve.s13.payao.malcolm.fitme.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.CircuitAdapter
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Circuit
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Workout

class ViewWorkout:AppCompatActivity(){

    private lateinit var workoutTitle: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var circuitAdapter: CircuitAdapter
    private lateinit var linearLayout: LinearLayoutManager
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_workout)

        recyclerView = findViewById(R.id.circuitRecyclerView)
        workoutTitle = findViewById(R.id.textView2)
        val currentCircuit = intent.getSerializableExtra("currentWorkout") as? Workout


        if (currentCircuit != null) {
            workoutTitle.text = currentCircuit.workoutTitle
            setupRecyclerView(currentCircuit.circuit)

        }

        val backBtn: Button = findViewById(R.id.button4)

        backBtn.setOnClickListener{
            finish()
        }

    }

    private fun setupRecyclerView(data: Array<Circuit>){
        linearLayout = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayout
        circuitAdapter = CircuitAdapter(data,this)
        recyclerView.adapter=circuitAdapter
    }

}
