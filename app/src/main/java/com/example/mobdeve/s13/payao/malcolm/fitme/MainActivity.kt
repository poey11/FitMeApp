package com.example.mobdeve.s13.payao.malcolm.fitme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mobdeve.s13.payao.malcolm.fitme.activities.Login
import com.example.mobdeve.s13.payao.malcolm.fitme.fragments.CalendarFragment
import com.example.mobdeve.s13.payao.malcolm.fitme.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.mobdeve.s13.payao.malcolm.fitme.fragments.UserFragment
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
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
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
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


    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()

    }
}