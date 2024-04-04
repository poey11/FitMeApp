package com.example.mobdeve.s13.payao.malcolm.fitme.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.AddExerciseAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.math.log

class CircuitExList : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseAdapter: AddExerciseAdapter
    private  val listOfExercises: MutableList<String> = mutableListOf()
    private  lateinit var  SearchBar:SearchView
    private lateinit var back: Button
    private lateinit var add: Button
    private lateinit var monCb:CheckBox
    private lateinit var tueCb:CheckBox
    private lateinit var wedCb:CheckBox
    private lateinit var thuCb:CheckBox
    private lateinit var friCb:CheckBox
    private lateinit var satCb:CheckBox
    private lateinit var sunCb:CheckBox
    private var selectedExercises: List<String> = emptyList()
    private lateinit var prevSetDays:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.circuits_exercise_list)
        val  currentWorkoutID= intent.getStringExtra("workoutID")
        SearchBar = findViewById(R.id.searchExerciseSearchViewB)
        monCb = findViewById(R.id.checkBox1)
        tueCb = findViewById(R.id.checkBox2)
        wedCb = findViewById(R.id.checkBox3)
        thuCb = findViewById(R.id.checkBox4)
        friCb = findViewById(R.id.checkBox5)
        satCb = findViewById(R.id.checkBox6)
        sunCb = findViewById(R.id.checkBox7)
        back = findViewById(R.id.backsBtn)
        add = findViewById(R.id.addBtn)
        recyclerView = findViewById(R.id.recyclerViewExercises)

        back.setOnClickListener {
            finish()
        }
        add.setOnClickListener{
            if (currentWorkoutID != null) {
                setDays(currentWorkoutID)
                addNewExercise(currentWorkoutID)
                finish()
            }
        }
        fetchExercises()
        setupRecyclerView()
        SearchExerceise()
    }

    private fun fetchExercises(){
        db.collection("exercises")
            .get()
            .addOnSuccessListener { result ->
                val exercises = result.documents.map { document ->
                    document.getString("name") ?: ""
                }
                listOfExercises.addAll(exercises)
                exerciseAdapter.setData(listOfExercises) // Update the adapter's data
            }




    }

    private fun SearchExerceise(){
        SearchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                fetchExercisesForMuscleOrQuery(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                fetchExercisesForMuscleOrQuery(newText)
                return true
            }
        })
    }

    private fun fetchExercisesForMuscleOrQuery( query: String?) {
            if (query.isNullOrEmpty()) {
                // Fetch all exercises when no muscle or query is specified
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
            } else {
                db.collection("exercises")
                    .orderBy("name") // Order by name to improve filtering efficiency
                    .get()
                    .addOnSuccessListener { result ->
                        val exerciseNames = mutableListOf<String>()
                        for (document in result) {
                            val name = document.getString("name")
                            name?.let {
                                if (it.contains(query, ignoreCase = true)) { // Case-insensitive partial match
                                    exerciseNames.add(it)
                                }
                            }
                        }
                        exerciseAdapter.setData(exerciseNames)
                    }
                    .addOnFailureListener { exception ->
                        // Handle errors
                    }

            }
        }

    private fun addNewExercise(workId: String) {
        selectedExercises = exerciseAdapter.getSelectedExercises()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val userExerciseRef = db.collection("userExercise").document(currentUser.uid)
                .collection("listOfWorkouts").document(workId)
                .collection("listOfExercises")
            // Use update to add new exercises without overwriting existing data
            db.runTransaction { transaction ->
                for (exercise in selectedExercises) {
                    val newExerciseDocRef = userExerciseRef.document()
                    transaction.set(newExerciseDocRef, hashMapOf(
                        "ExerciseTitle" to exercise
                    ))
                }
            }.addOnSuccessListener {
                // Transaction completed successfully
                // You can put any success handling logic here
            }.addOnFailureListener { e ->
                // Transaction failed
                Log.w("ERRORATADDING", "Transaction failure.", e)
                // You can put any failure handling logic here
            }
        }
    }


    private fun setDays(workId:String){

        var selectedDays:String = ""
        if(monCb.isChecked){
            selectedDays += "${monCb.text} "
        }
        if(tueCb.isChecked){
            selectedDays += "${tueCb.text} "
        }
        if(wedCb.isChecked){
            selectedDays += "${wedCb.text} "
        }
        if(thuCb.isChecked){
            selectedDays += "${thuCb.text} "
        }
        if(friCb.isChecked){
            selectedDays += "${friCb.text} "
        }
        if(satCb.isChecked){
            selectedDays += "${satCb.text} "
        }
        if(sunCb.isChecked){
            selectedDays += "${sunCb.text} "
        }
        db.collection("userExercise").document(auth.currentUser?.uid.toString())
            .collection("listOfWorkouts").document(workId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val prevSetDays = document.getString("daysSet").toString()
                    if (selectedDays.isEmpty()) {
                        selectedDays = prevSetDays
                    }
                    updateDaysInDatabase(workId, selectedDays)
                } else {
                    Log.d("TAG", "No such document")
                }
            }
    }

    private fun updateDaysInDatabase(workId: String, selectedDays: String) {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            db.collection("userExercise").document(currentUser.uid)
                .collection("listOfWorkouts").document(workId)
                .update("daysSet", selectedDays)
                .addOnSuccessListener {
                    Log.d("TAG", "Days updated successfully")
                }
                .addOnFailureListener { e ->
                    Log.e("TAG", "Error updating days", e)
                }
        }
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        exerciseAdapter = AddExerciseAdapter(emptyList())
        recyclerView.adapter = exerciseAdapter
    }
}