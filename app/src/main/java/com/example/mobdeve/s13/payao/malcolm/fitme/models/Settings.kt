package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mobdeve.s13.payao.malcolm.fitme.R

class Settings : AppCompatActivity() {

    private lateinit var editProfilebtn: Button
    private lateinit var accountbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_template)


        editProfilebtn = findViewById(R.id.editProfilebtn)
        accountbtn = findViewById(R.id.accountbtn)


        editProfilebtn.setOnClickListener {
            val intent = Intent(this, EditProfile::class.java)
            startActivity(intent)
        }

        accountbtn.setOnClickListener {
            val intent = Intent(this, Account::class.java)
            startActivity(intent)
        }
    }
}
