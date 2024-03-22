package com.example.mobdeve.s13.payao.malcolm.fitme.activities

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mobdeve.s13.payao.malcolm.fitme.R

class CreateNewWorkout: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_new_workout)

        val backBtn:Button = findViewById(R.id.backBtn)

        backBtn.setOnClickListener{
            finish()
        }
    }
}