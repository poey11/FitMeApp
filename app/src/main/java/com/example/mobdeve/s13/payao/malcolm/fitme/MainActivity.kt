package com.example.mobdeve.s13.payao.malcolm.fitme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mobdeve.s13.payao.malcolm.fitme.activities.ForgotActivity
import com.example.mobdeve.s13.payao.malcolm.fitme.activities.RegisterActivity
import com.example.mobdeve.s13.payao.malcolm.fitme.processes.LoginBackend
import com.example.mobdeve.s13.payao.malcolm.fitme.processes.LoginListener
import com.example.mobdeve.s13.payao.malcolm.fitme.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.mobdeve.s13.payao.malcolm.fitme.fragments.UserFragment
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore


class MainActivity : AppCompatActivity(), LoginListener {
    private  lateinit var bottomNavigationView: BottomNavigationView

    private  lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if(currentUser != null){
            showMainFragment()

        }
        else{
            showLoginScreen()
        }
    }

    private fun showMainFragment(){
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener{menuItem ->
            when(menuItem.itemId) {
                R.id.bottom_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.bottom_calendar -> {
                    replaceFragment(CalendarFragment())
                    true
                }

                R.id.bottom_history -> {
                    replaceFragment(HistoryFragment())
                    true
                }

                R.id.bottom_user -> {
                    replaceFragment(UserFragment())
                    true
                }
                else-> false
            }
        }

        replaceFragment(HomeFragment())
    }



    private fun showLoginScreen(){
        setContentView(R.layout.login_page)
        val email:String = findViewById<View?>(R.id.emailAddInputET).toString()
        val pass:String = findViewById<View?>(R.id.passInputET).toString()
        val loginBtn:Button = findViewById(R.id.loginBtn)
        val forgotBtn:Button = findViewById(R.id.forgotPassBtn)
        val registerBtn:Button = findViewById(R.id.signUpHereBtn)

        loginBtn.setOnClickListener{
            Log.d("Email", email)
            Log.d("Pass", pass)
            LoginBackend.login(email,pass,this)

        }
        forgotBtn.setOnClickListener{
            val intent = Intent(this,ForgotActivity::class.java)
            startActivity(intent)
        }
        registerBtn.setOnClickListener{
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onLoginSuccess() {
        showMainFragment()
    }

    override fun onLoginFailure(errorMessage: String) {
        Toast.makeText(this,errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()

    }
}