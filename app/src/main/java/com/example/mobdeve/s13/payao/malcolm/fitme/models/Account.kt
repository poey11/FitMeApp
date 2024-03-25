package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mobdeve.s13.payao.malcolm.fitme.R

class Account : AppCompatActivity() {

    private lateinit var changeAccountSettingsbtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_template)

        // Initialize Edit Profile button
        changeAccountSettingsbtn = findViewById(R.id.changeAccountSettingsbtn)

        // Set click listener for Edit Profile button
        changeAccountSettingsbtn.setOnClickListener {
            val intent = Intent(this, ChangeAccountSettings ::class.java)
            startActivity(intent)
        }
    }
}