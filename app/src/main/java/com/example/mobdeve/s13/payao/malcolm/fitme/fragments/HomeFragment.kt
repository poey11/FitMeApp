package com.example.mobdeve.s13.payao.malcolm.fitme.fragments
/*
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.database.DataHelper
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.activities.CreateNewWorkout
import com.example.mobdeve.s13.payao.malcolm.fitme.activities.ViewWorkout
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Workout
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.WorkoutAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


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
            lifecycleScope.launch {
                try {
                    val result = withContext(Dispatchers.IO) { fetchData() }

                    Log.d("RESULTS", result)
                } catch (e: Exception) {
                    Log.e("ErrorB", "Failed to fetch data: ${e.message}",e)
                }
            }
            //val intent = Intent(requireContext(), CreateNewWorkout::class.java)
            //startActivity(intent)
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

    private  fun fetchData(): String {
        val url = URL("https://api.api-ninjas.com/v1/exercises?muscle=chest")
        val connection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
        connection.setRequestProperty("x-api-key", "+8lp3Z9SsejyRbDHGIKfBA==dqbLteaPO8ljasgy")

        var result:String = ""
        val responseCode = connection.responseCode
        if(responseCode == HttpURLConnection.HTTP_OK){
            val inputStream = connection.inputStream
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            var inputLine: String?
            val response = StringBuilder()
            while (bufferedReader.readLine().also { inputLine = it } != null) {
                response.append(inputLine)
            }
            bufferedReader.close()

            result = response.toString()
        }
        return result
    }


}*/


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.WorkoutAdapter
import com.example.mobdeve.s13.payao.malcolm.fitme.database.DataHelper
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Workout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class HomeFragment<Exercise> : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var workoutAdapter: WorkoutAdapter
    private lateinit var linearManager: LinearLayoutManager
    private lateinit var workout: ArrayList<Workout>

    // Firebase Firestore instance
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
            lifecycleScope.launch {
                try {
                    val result = withContext(Dispatchers.IO) { fetchData() }
                    // Parse the result and store in Firestore
                    parseAndStoreData(result)
                } catch (e: Exception) {
                    Log.e("ErrorB", "Failed to fetch data: ${e.message}", e)
                }
            }
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

    private fun fetchData(): String {
        val url = URL("https://api.api-ninjas.com/v1/exercises?muscle=triceps")
        val connection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
        connection.setRequestProperty("x-api-key", "+8lp3Z9SsejyRbDHGIKfBA==dqbLteaPO8ljasgy")

        var result: String = ""
        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val inputStream = connection.inputStream
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            var inputLine: String?
            val response = StringBuilder()
            while (bufferedReader.readLine().also { inputLine = it } != null) {
                response.append(inputLine)
            }
            bufferedReader.close()

            result = response.toString()
        }
        return result
    }

    private fun parseAndStoreData(data: String) {
        lifecycleScope.launch {
            try {
                // Parse the JSON data
                val jsonArray = JSONArray(data)

                // Store each exercise in Firestore
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val name = jsonObject.getString("name")
                    val muscle = jsonObject.getString("muscle")
                    val instructions = jsonObject.getString("instructions")


                    // Create a HashMap to represent exercise data
                    val exerciseData = hashMapOf(
                        "name" to name,
                        "muscle" to muscle,
                        "instructions" to instructions,
                        "reps" to 0,
                        "sets" to 0,
                        "weight" to 0,
                    )

                    // Add the exercise data to Firestore
                    db.collection("exercises")
                        .add(exerciseData)
                        .addOnSuccessListener { documentReference ->
                            Log.d("Firestore", "Exercise added with ID: ${documentReference.id}")
                        }
                        .addOnFailureListener { e ->
                            Log.e("Firestore", "Error adding exercise", e)
                        }
                }
            } catch (e: Exception) {
                Log.e("ErrorB", "Failed to parse and store data: ${e.message}", e)
            }
        }
    }


}
