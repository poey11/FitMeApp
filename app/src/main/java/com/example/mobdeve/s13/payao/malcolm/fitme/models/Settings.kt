package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mobdeve.s13.payao.malcolm.fitme.MainActivity
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.fragments.UserFragment
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth

class Settings : AppCompatActivity() {

    private lateinit var editProfilebtn: Button
    private lateinit var accountbtn: Button
    private lateinit var backBtn: Button
    private lateinit var logoutBtn:Button
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_template)


        editProfilebtn = findViewById(R.id.editProfilebtn)
        accountbtn = findViewById(R.id.accountbtn)
        backBtn = findViewById(R.id.backBtn)
        logoutBtn = findViewById(R.id.logoutbtn)

        editProfilebtn.setOnClickListener {
            val intent = Intent(this, EditProfile::class.java)
            startActivity(intent)
        }

        accountbtn.setOnClickListener {
            val intent = Intent(this, Account::class.java)
            startActivity(intent)
        }

        logoutBtn.setOnClickListener{

                auth.signOut()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()


        }

        backBtn.setOnClickListener {
            finish()
        }
    }
}
