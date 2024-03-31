package com.example.mobdeve.s13.payao.malcolm.fitme.activities



import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.example.mobdeve.s13.payao.malcolm.fitme.R

class AddNewExerciseActivity : AppCompatActivity() {

    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var muscleSpinnerAdapter: ArrayAdapter<String>
    private lateinit var instructionsEditText: EditText
    private lateinit var nameEditText: EditText
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_exercise)

        val backBtn: Button = findViewById(R.id.backBtn)
        backBtn.setOnClickListener {
            finish()
        }

        val sendBtn: Button = findViewById(R.id.sendBtn)
        sendBtn.setOnClickListener {
            saveExerciseToFirestore()
        }

        autoCompleteTextView = findViewById(R.id.autoCompleteTextView2)
        setupMuscleSpinner()

        // Initialize the EditText for instructions
        instructionsEditText = findViewById(R.id.instructions)
        // Initialize the EditText for name
        nameEditText = findViewById(R.id.name)

        // Add an OnEditorActionListener to the instructions EditText
        instructionsEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Clear focus from the EditText
                instructionsEditText.clearFocus()
                // Hide the keyboard
                val inputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(
                    instructionsEditText.windowToken,
                    0
                )
                true
            } else {
                false
            }
        }

        // Add an OnEditorActionListener to the name EditText
        nameEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Clear focus from the EditText
                nameEditText.clearFocus()
                // Hide the keyboard
                val inputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(
                    nameEditText.windowToken,
                    0
                )
                true
            } else {
                false
            }
        }
    }

    private fun setupMuscleSpinner() {
        val muscleArray = resources.getStringArray(R.array.muscle)
        muscleSpinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, muscleArray)
        muscleSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        autoCompleteTextView.setAdapter(muscleSpinnerAdapter)
    }

    private fun saveExerciseToFirestore() {
        val name = nameEditText.text.toString().trim()
        val muscle = autoCompleteTextView.text.toString().trim()
        val instructions = instructionsEditText.text.toString().trim()

        if (name.isNotEmpty() && muscle.isNotEmpty() && instructions.isNotEmpty()) {
            val exercise = hashMapOf(
                "name" to name,
                "muscle" to muscle,
                "instructions" to instructions,
                "reps" to 0,
                "sets" to 0,
                "weight" to 0
            )

            db.collection("exercises")
                .add(exercise)
                .addOnSuccessListener {
                    // Exercise saved successfully
                    finish()
                }
                .addOnFailureListener {
                    // Handle failure
                }
        } else {
            // Handle empty fields
        }
    }
}
