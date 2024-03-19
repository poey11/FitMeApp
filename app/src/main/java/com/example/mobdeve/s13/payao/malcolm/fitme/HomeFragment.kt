package com.example.mobdeve.s13.payao.malcolm.fitme

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_home, container, false)
    }




    private lateinit var recyclerView: RecyclerView
    private  lateinit var workoutAdapter: WorkoutAdapter
    private lateinit var  linearManager: LinearLayoutManager
    private lateinit var  workout: ArrayList<Workout>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val fab: FloatingActionButton = view.findViewById(R.id.floatingActionButton)

        fab.setOnClickListener{
            Toast.makeText(requireContext(), "BOBO MO DERRICK", Toast.LENGTH_SHORT).show()
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        this.workout = DataHelper.initializeWorkout()
        recyclerView = view?.findViewById(R.id.recyclerView) as RecyclerView
        linearManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearManager
        workoutAdapter = WorkoutAdapter(workout,)
        recyclerView.adapter = workoutAdapter

    }



}