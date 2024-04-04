package com.example.mobdeve.s13.payao.malcolm.fitme.fragments

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.example.mobdeve.s13.payao.malcolm.fitme.database.DataHelper
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.activities.CreateNewWorkout
import com.example.mobdeve.s13.payao.malcolm.fitme.activities.ViewWorkout
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Workout
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.WorkoutAdapter
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Circuit
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.CExerciseAdapter
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.WorkoutItemClickListener

/*
class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var workoutAdapter: WorkoutAdapter
    private lateinit var linearManager: LinearLayoutManager
    private val db = FirebaseFirestore.getInstance()

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
        val workouts = ArrayList<Workout>()

        // Get the current user's UID from FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid

        // Check if UID is not null before proceeding
        uid?.let {
            // Query Firestore collection "userExercise" to retrieve workout data for the current user
            db.collection("userExercise").document(uid).collection("listOfWorkouts")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val workoutTitle = document.getString("workoutTitle")
                        val workoutDays = document.get("daysSet") as? String

                        if (workoutTitle != null && workoutDays != null) {
                            val workout = Workout(workoutTitle, arrayOf(workoutDays), emptyArray(),document.id)
                            workouts.add(workout)
                        }
                    }

                    // Populate RecyclerView with retrieved workouts
                    linearManager = LinearLayoutManager(requireContext())
                    recyclerView.layoutManager = linearManager
                    workoutAdapter = WorkoutAdapter(workouts, requireContext())
                    recyclerView.adapter = workoutAdapter
                }
                .addOnFailureListener { exception ->
                    // Handle any errors
                    Log.w(TAG, "Error getting documents: ", exception)
                }
        }
    }


    companion object {
        private const val TAG = "HomeFragment"
    }
}
 */

class HomeFragment : Fragment(), WorkoutItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var workoutAdapter: WorkoutAdapter
    private lateinit var linearManager: LinearLayoutManager
    private val db = FirebaseFirestore.getInstance()

    // Declare the workouts list as a class-level property
   // private val workouts = ArrayList<Workout>()

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
        val workouts = ArrayList<Workout>()

        // Get the current user's UID from FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid

        // Check if UID is not null before proceeding
        uid?.let {
            // Query Firestore collection "userExercise" to retrieve workout data for the current user
            db.collection("userExercise").document(uid).collection("listOfWorkouts")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val workoutTitle = document.getString("workoutTitle")
                        val workoutDays = document.get("daysSet") as? String

                        if (workoutTitle != null && workoutDays != null) {
                            val workout = Workout(workoutTitle, arrayOf(workoutDays), emptyArray(),document.id)
                            workouts.add(workout)
                        }
                    }

                    // Populate RecyclerView with retrieved workouts
                    linearManager = LinearLayoutManager(requireContext())
                    recyclerView.layoutManager = linearManager
                    workoutAdapter = WorkoutAdapter(workouts, requireContext())
                    workoutAdapter.setWorkoutItemClickListener(this) // Set the listener here
                    recyclerView.adapter = workoutAdapter
                }
                .addOnFailureListener { exception ->
                    // Handle any errors
                    Log.w(TAG, "Error getting documents: ", exception)
                }
        }
    }


    override fun onDeleteClicked(position: Int) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid

        uid?.let { userId ->
            val workoutId = workoutAdapter.getWorkoutItem(position).workoutID
            db.collection("userExercise").document(userId)
                .collection("listOfWorkouts").document(workoutId)
                .delete()
                .addOnSuccessListener {
                    Log.d(TAG, "Document $workoutId successfully deleted from listOfWorkouts")
                    // Remove item locally from RecyclerView
                    workoutAdapter.removeWorkoutItem(position)
                }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "Error deleting document $workoutId from listOfWorkouts: ", exception)
                }
        }
    }



    companion object {
        private const val TAG = "HomeFragment"
    }
}





