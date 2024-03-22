package com.example.mobdeve.s13.payao.malcolm.fitme.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.database.DataHelper
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.activities.CreateNewWorkout
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Workout
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.WorkoutAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var workoutAdapter: WorkoutAdapter
    private lateinit var linearManager: LinearLayoutManager
    private lateinit var workout: ArrayList<Workout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fab: FloatingActionButton = view.findViewById(R.id.floatingActionButton)


        fab.setOnClickListener {
            val intent = Intent(requireContext(), CreateNewWorkout::class.java)
            startActivity(intent)
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        this.workout = DataHelper.initializeWorkout()
        linearManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearManager
        workoutAdapter = WorkoutAdapter(workout, requireContext())
        recyclerView.adapter = workoutAdapter

    }
}