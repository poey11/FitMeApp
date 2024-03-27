package com.example.mobdeve.s13.payao.malcolm.fitme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mobdeve.s13.payao.malcolm.fitme.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.mobdeve.s13.payao.malcolm.fitme.fragments.UserFragment


class MainActivity : AppCompatActivity() {

    private  lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener{menuItem ->
            when(menuItem.itemId) {
                R.id.bottom_home -> {
                    replaceFragment(HomeFragment<Any?>())
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

        replaceFragment(HomeFragment<Any?>())


    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()

    }
}