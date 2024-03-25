package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import android.widget.EditText
import android.widget.Button
import com.example.mobdeve.s13.payao.malcolm.fitme.database.UserDataHelper
import com.example.mobdeve.s13.payao.malcolm.fitme.R


class ChangeAccountSettings : AppCompatActivity() {

    private lateinit var emailLabelTV: TextView
    private lateinit var passwordLabelTV: TextView
    private lateinit var emailInputET: EditText
    private lateinit var passwordInputET: EditText
    private lateinit var saveBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_account_settings_template)

        // Initialize views
        emailLabelTV = findViewById(R.id.emailLabelTV)
        passwordLabelTV = findViewById(R.id.passwordLabelTV)
        emailInputET = findViewById(R.id.emailInputET)
        passwordInputET = findViewById(R.id.passwordInputET)
        saveBtn = findViewById(R.id.saveBtn)

        // Retrieve user data from UserDataHelper
        val users = UserDataHelper.initializeUserData()
        if (users.isNotEmpty()) {
            val userData = users[0] // Assuming there's only one user
            // Set user data to TextViews
            emailInputET.setText(userData.email)
            passwordInputET.setText(userData.password)
        }

        // Set click listener for Save button
        saveBtn.setOnClickListener {
            // Add logic to save changes
            Toast.makeText(this, "Information saved", Toast.LENGTH_SHORT).show()
        }
    }
}
