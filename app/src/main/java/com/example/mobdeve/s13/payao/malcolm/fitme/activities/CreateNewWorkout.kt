package com.example.mobdeve.s13.payao.malcolm.fitme.activities


import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.AddExerciseAdapter
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.content.Intent
import android.widget.SearchView

/*
class CreateNewWorkout : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseAdapter: AddExerciseAdapter
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var muscleSpinnerAdapter: ArrayAdapter<String>

    // Firebase Firestore instance
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_new_workout)

        val backBtn: Button = findViewById(R.id.backBtn)
        recyclerView = findViewById(R.id.recyclerView)
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView2)
       val addNewExerciseBtn: Button = findViewById(R.id.createNewExerciseBtn)
      val searchExerciseSearchView: SearchView = findViewById(R.id.searchExerciseSearchView)

        backBtn.setOnClickListener {
            finish()
        }

        setupRecyclerView()
        setupMuscleSpinner()
        fetchExercises()

        // Set click listener for the "Create New Exercise" button
        addNewExerciseBtn.setOnClickListener {
            // Navigate to the activity for adding a new exercise
            startActivity(Intent(this, AddNewExerciseActivity::class.java))
        }

    }



    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        exerciseAdapter = AddExerciseAdapter(emptyList())
        recyclerView.adapter = exerciseAdapter
    }


    private fun fetchExercisesForMuscle(muscle: String?) {
        if (muscle.isNullOrEmpty() || muscle == "all") {
            // Fetch all exercises when no muscle group is specified
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
            // Fetch exercises filtered by the specified muscle group
            db.collection("exercises")
                .whereEqualTo("muscle", muscle)
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





    private fun fetchExercises() {
        // Initially fetch all exercises
        fetchExercisesForMuscle("")

        // Listen for changes in the selected muscle group and fetch exercises accordingly
        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            val selectedMuscle = parent.getItemAtPosition(position).toString()
            fetchExercisesForMuscle(selectedMuscle)
        }
    }





    private fun setupMuscleSpinner() {
        // Get the muscle array from strings.xml
        val muscleArray = resources.getStringArray(R.array.muscle)

        // Create an ArrayAdapter using the muscle array
        muscleSpinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, muscleArray)

        // Specify the layout to use when the list of choices appears
        muscleSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the AutoCompleteTextView
        autoCompleteTextView.setAdapter(muscleSpinnerAdapter)
    }
}
*/

class CreateNewWorkout : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseAdapter: AddExerciseAdapter
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var muscleSpinnerAdapter: ArrayAdapter<String>
    private lateinit var searchExerciseSearchView: SearchView

    // Firebase Firestore instance
    private val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_new_workout)

        searchExerciseSearchView = findViewById(R.id.searchExerciseSearchView)
        val backBtn: Button = findViewById(R.id.backBtn)
        recyclerView = findViewById(R.id.recyclerView)
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView2)
        val addNewExerciseBtn: Button = findViewById(R.id.createNewExerciseBtn)


        backBtn.setOnClickListener {
            finish()
        }

        setupRecyclerView()
        setupMuscleSpinner()
        fetchExercises()

        // Set click listener for the "Create New Exercise" button
        addNewExerciseBtn.setOnClickListener {
            // Navigate to the activity for adding a new exercise
            startActivity(Intent(this, AddNewExerciseActivity::class.java))
        }

    }



    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        exerciseAdapter = AddExerciseAdapter(emptyList())
        recyclerView.adapter = exerciseAdapter
    }

    private fun fetchExercisesForMuscleOrQuery(muscle: String?, query: String?) {
        if (muscle.isNullOrEmpty() || muscle == "all") {
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
                // Fetch exercises filtered by the specified query
                /*
                db.collection("exercises")
                    .whereArrayContains("name", query.toLowerCase())
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

                 */
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
        } else {
            // Fetch exercises filtered by the specified muscle
            db.collection("exercises")
                .whereEqualTo("muscle", muscle)
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


    private fun fetchExercises() {
        // Listen for changes in the selected muscle group and fetch exercises accordingly
        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            val selectedMuscle = parent.getItemAtPosition(position).toString()
            fetchExercisesForMuscleOrQuery(selectedMuscle, "")
        }

        // Listen for changes in the search view and fetch exercises accordingly
        searchExerciseSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                fetchExercisesForMuscleOrQuery("", query)
                // Clear focus from the SearchView
                searchExerciseSearchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                fetchExercisesForMuscleOrQuery("", newText)
                return true
            }
        })

        // Initially fetch all exercises
        fetchExercisesForMuscleOrQuery("", "")
    }




    private fun setupMuscleSpinner() {
        // Get the muscle array from strings.xml
        val muscleArray = resources.getStringArray(R.array.muscle)

        // Create an ArrayAdapter using the muscle array
        muscleSpinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, muscleArray)

        // Specify the layout to use when the list of choices appears
        muscleSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the AutoCompleteTextView
        autoCompleteTextView.setAdapter(muscleSpinnerAdapter)
    }
}





