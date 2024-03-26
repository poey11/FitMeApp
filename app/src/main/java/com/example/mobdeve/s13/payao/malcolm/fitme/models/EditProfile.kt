package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Toast
import com.example.mobdeve.s13.payao.malcolm.fitme.database.UserDataHelper
import com.example.mobdeve.s13.payao.malcolm.fitme.R

class EditProfile : AppCompatActivity() {

    private lateinit var firstNameInputET: EditText
    private lateinit var lastNameInputET: EditText
    private lateinit var ageInputET: EditText
    private lateinit var weightInputET: EditText
    private lateinit var heightInputET: EditText
    private lateinit var saveBtn: Button
    private lateinit var backBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editprofile_template)

        // Initialize EditText fields
        firstNameInputET = findViewById(R.id.firstNameInputET)
        lastNameInputET = findViewById(R.id.lastNameInputET)
        ageInputET = findViewById(R.id.ageInputET)
        weightInputET = findViewById(R.id.weightInputET)
        heightInputET = findViewById(R.id.heightInputET)

        saveBtn = findViewById(R.id.saveBtn)
        backBtn = findViewById(R.id.backBtn)

        // Retrieve user data from UserDataHelper
        val users = UserDataHelper.initializeUserData()
        if (users.isNotEmpty()) {
            val userData = users[0] // Assuming there's at least one user
            // Set user data to EditText fields
            firstNameInputET.setText(userData.firstName)
            lastNameInputET.setText(userData.lastName)
            ageInputET.setText(userData.age.toString())
            weightInputET.setText(userData.weight.toString())
            heightInputET.setText(userData.height.toString())
        }

        // Set click listener for Save button
        saveBtn.setOnClickListener {
            // Add logic to save changes
            Toast.makeText(this, "Information saved", Toast.LENGTH_SHORT).show()
        }

        backBtn.setOnClickListener {
            finish()
        }
    }
}