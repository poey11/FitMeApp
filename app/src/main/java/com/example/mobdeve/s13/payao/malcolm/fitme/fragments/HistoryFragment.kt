package com.example.mobdeve.s13.payao.malcolm.fitme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.ExerciseAdapter
import com.example.mobdeve.s13.payao.malcolm.fitme.database.ExerciseDataHelper
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Exercise
import android.content.Intent
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Stats
import android.widget.Button



/*
class HistoryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseAdapter: ExerciseAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var exercises: ArrayList<Exercise>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        recyclerView = view.findViewById(R.id.historyrecyclerView)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val informationBtn = view.findViewById(R.id.information)
        informationBtn.setOnClickListener {
            val intent = Intent(requireContext(), Stats::class.java)
            startActivity(intent)
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        exercises = ExerciseDataHelper.initializeExercises()
        linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearLayoutManager
        exerciseAdapter = ExerciseAdapter(exercises, requireContext())
        recyclerView.adapter = exerciseAdapter
    }




}
*/


class HistoryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseAdapter: ExerciseAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var exercises: ArrayList<Exercise>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        recyclerView = view.findViewById(R.id.historyrecyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        exercises = ExerciseDataHelper.initializeExercises()
        linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearLayoutManager
        exerciseAdapter = ExerciseAdapter(exercises, requireContext())
        recyclerView.adapter = exerciseAdapter
    }
}

