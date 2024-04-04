package com.example.mobdeve.s13.payao.malcolm.fitme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.ExerciseAdapter
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Exercise
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HistoryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseAdapter: ExerciseAdapter
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        recyclerView = view.findViewById(R.id.historyrecyclerView)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fetchExerciseData()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        exerciseAdapter = ExerciseAdapter(ArrayList(), requireContext())
        recyclerView.adapter = exerciseAdapter
    }

private fun fetchExerciseData() {
    val currentUser = FirebaseAuth.getInstance().currentUser
    val uid = currentUser?.uid

    if (uid != null) {
        db.collection("userExercise").document(uid).collection("listOfPastExercise")
            .get()
            .addOnSuccessListener { documents ->
                val exercises = HashMap<String, Exercise>() // Use HashMap to store exercises by title

                for (document in documents) {
                    val exerciseTitle = document.getString("exTitle") ?: ""

                    // Check if an exercise with the same title already exists
                    if (!exercises.containsKey(exerciseTitle)) {
                        val reps = document.getLong("reps")?.toInt() ?: 0
                        val sets = document.getLong("sets")?.toInt() ?: 0
                        val KG = document.getLong("kg")?.toInt() ?: 0
                        val instruction = document.getString("instruction") ?: ""
                        val exercise = Exercise(exerciseTitle, reps, sets, KG, instruction)
                        exercises[exerciseTitle] = exercise // Add exercise to the HashMap
                    }
                }

                // Convert the HashMap back to ArrayList
                val uniqueExercises = ArrayList(exercises.values)
                exerciseAdapter.setData(uniqueExercises)
            }
            .addOnFailureListener { exception ->
                // Handle error gracefully
            }
    }
}

}



