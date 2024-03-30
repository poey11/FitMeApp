package com.example.mobdeve.s13.payao.malcolm.fitme.activities


import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.AddExerciseAdapter
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.content.Intent
import android.widget.SearchView
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth


/*
class CreateNewWorkout : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseAdapter: AddExerciseAdapter
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var muscleSpinnerAdapter: ArrayAdapter<String>
    private lateinit var searchExerciseSearchView: SearchView

    // Firebase Firestore instance
    private val db = FirebaseFirestore.getInstance()

    // Define variables to hold workout title and selected exercises
    private var workoutTitle: String = ""
    private var selectedExercises: List<String> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_new_workout)

        searchExerciseSearchView = findViewById(R.id.searchExerciseSearchView)
        val backBtn: Button = findViewById(R.id.backBtn)
        recyclerView = findViewById(R.id.recyclerView)
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView2)
        val addNewExerciseBtn: Button = findViewById(R.id.createNewExerciseBtn)

        val addBtn: Button = findViewById(R.id.addBtn)

        // Set click listener for the "Add" button
        addBtn.setOnClickListener {
            // Get the workout title from the user input
            val workoutTitleEditText: EditText = findViewById(R.id.editTextText)
            workoutTitle = workoutTitleEditText.text.toString()

                    // Get the selected exercises from the adapter
                selectedExercises = exerciseAdapter.getSelectedExercises()

            // Save the workout title and selected exercises to Firestore
            saveWorkoutToFirestore()
        }

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



    // Function to save workout title and selected exercises to Firestore
    private fun saveWorkoutToFirestore() {
        // Create a new document in the "listOfWorkouts" collection
        val workoutData = hashMapOf(
            "daysSet" to "Everyday", // Set daysSet field to "Everyday"
            "workoutTitle" to workoutTitle // Set workoutTitle field to the workout title
        )

        db.collection("userExercises").document("listOfWorkouts")
            .collection("workouts")
            .add(workoutData)
            .addOnSuccessListener { documentReference ->
                // Document successfully added
                // Now, save the selected exercises under this workout
                saveExercisesForWorkout(documentReference.id)
            }
            .addOnFailureListener { e ->
                // Handle errors
            }
    }

    // Function to save selected exercises for the workout to Firestore
    private fun saveExercisesForWorkout(workoutId: String) {
        // Create documents for each selected exercise under the workout
        for (exercise in selectedExercises) {
            val exerciseData = hashMapOf(
                "EID" to exercise // Assuming EID field represents the exercise name
            )

            db.collection("userExercises").document("listOfWorkouts")
                .collection("workouts").document(workoutId)
                .collection("listOfExercises")
                .add(exerciseData)
                .addOnSuccessListener { documentReference ->
                    // Document successfully added
                }
                .addOnFailureListener { e ->
                    // Handle errors
                }
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
}*/

class CreateNewWorkout : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseAdapter: AddExerciseAdapter
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var muscleSpinnerAdapter: ArrayAdapter<String>
    private lateinit var searchExerciseSearchView: SearchView

    // Firebase Firestore instance
    private val db = FirebaseFirestore.getInstance()

    // Define variables to hold workout title and selected exercises
    private var workoutTitle: String = ""
    private var selectedExercises: List<String> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_new_workout)

        searchExerciseSearchView = findViewById(R.id.searchExerciseSearchView)
        val backBtn: Button = findViewById(R.id.backBtn)
        recyclerView = findViewById(R.id.recyclerView)
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView2)
        val addNewExerciseBtn: Button = findViewById(R.id.createNewExerciseBtn)

        val addBtn: Button = findViewById(R.id.addBtn)

        // Set click listener for the "Add" button
        addBtn.setOnClickListener {
            // Get the workout title from the user input
            val workoutTitleEditText: EditText = findViewById(R.id.editTextText)
            workoutTitle = workoutTitleEditText.text.toString()

            // Get the selected exercises from the adapter
            selectedExercises = exerciseAdapter.getSelectedExercises()

            // Save the workout title and selected exercises to Firestore
            saveWorkoutToFirestore()
        }

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



    // Function to save workout title and selected exercises to Firestore
    private fun saveWorkoutToFirestore() {
        // Get the current user's UID from FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid

        // Check if UID is not null before proceeding
        uid?.let {
            // Create a new document under "userExercise" collection with the user's UID as document ID
            val userExerciseDocument = db.collection("userExercise").document(uid)

            // Create a new workout document in the "listOfWorkouts" subcollection
            val listOfWorkoutsData = hashMapOf(
                "daysSet" to "Everyday", // Set daysSet field to "Everyday"
                "workoutTitle" to workoutTitle // Set workoutTitle field to the workout title
            )

            userExerciseDocument.collection("listOfWorkouts")
                .add(listOfWorkoutsData)
                .addOnSuccessListener { documentReference ->
                    // Document successfully added
                    // Now, save the selected exercises under this workout
                    saveListOfExercises(uid, documentReference.id)
                    // Show a toast message indicating successful addition
                    Toast.makeText(this, "Workout added successfully!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    // Handle errors
                    Toast.makeText(this, "Failed to add workout: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    // Function to save selected exercises for the workout to Firestore
    private fun saveListOfExercises(uid: String, workoutId: String) {
        // Create a new document under "userExercise/{UID}/listOfWorkouts/{workoutId}/listOfExercises" collection with auto-generated ID
        val listOfExercisesCollection = db.collection("userExercise").document(uid)
            .collection("listOfWorkouts").document(workoutId)
            .collection("listOfExercises")

        // Add documents for each selected exercise under the workout
        for (exercise in selectedExercises) {
            // Create exercise data
            val exerciseData = hashMapOf(
                "EID" to exercise // Assuming EID field represents the name of the exercise
            )

            // Add exercise data to the document
            listOfExercisesCollection.add(exerciseData)
                .addOnSuccessListener {
                    // Document successfully added
                }
                .addOnFailureListener { e ->
                    // Handle errors
                }
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






