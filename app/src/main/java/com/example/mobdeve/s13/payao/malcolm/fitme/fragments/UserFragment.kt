package com.example.mobdeve.s13.payao.malcolm.fitme.fragments



import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.AccomplishmentsAdapter
import com.example.mobdeve.s13.payao.malcolm.fitme.database.AccomplishmentsDataHelper
import com.example.mobdeve.s13.payao.malcolm.fitme.database.UserDataHelper
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Settings
import com.example.mobdeve.s13.payao.malcolm.fitme.R



class UserFragment : Fragment() {

    private lateinit var accomplishmentsRecyclerView: RecyclerView
    private lateinit var firstNameTV: TextView
    private lateinit var lastNameTV: TextView
    private lateinit var sessionsValueTV: TextView
    private lateinit var volumeValueTV: TextView
    private lateinit var repsValueTV: TextView
    private lateinit var userIconImageView: ImageView
    private lateinit var settingsbtn: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        // Initialize TextViews
        firstNameTV = view.findViewById(R.id.firstNameTV)
        lastNameTV = view.findViewById(R.id.lastNameTV)
        sessionsValueTV = view.findViewById(R.id.sessionsValueTV)
        volumeValueTV = view.findViewById(R.id.volumeValueTV)
        repsValueTV = view.findViewById(R.id.repsValueTV)

        // Initialize ImageView for user icon
        userIconImageView = view.findViewById(R.id.userIcon)

        // Get user data
        val userDataList = UserDataHelper.initializeUserData()
        val userData = userDataList[0] // Assuming you have only one user for now

        // Set text using UserData object
        firstNameTV.text = userData.firstName
        lastNameTV.text = userData.lastName
        sessionsValueTV.text = userData.sessionValue.toString()
        volumeValueTV.text = userData.volumeValue
        repsValueTV.text = userData.repsValue
        userIconImageView.setImageResource(userData.userIcon) // Set user icon

        // Initialize RecyclerView
        accomplishmentsRecyclerView = view.findViewById(R.id.accomplishmentsRecyclerView)
        accomplishmentsRecyclerView.layoutManager = LinearLayoutManager(context)

        // Initialize list of accomplishments using AccomplishmentDataHelper
        val accomplishments = AccomplishmentsDataHelper.initializeAccomplishments()

        // Create and set adapter
        val adapter = AccomplishmentsAdapter(accomplishments)
        accomplishmentsRecyclerView.adapter = adapter

        // Initialize ImageButton for settings
        settingsbtn = view.findViewById(R.id.settingsbtn)
        settingsbtn.setOnClickListener {
            val intent = Intent(activity, Settings::class.java)
            startActivity(intent)
        }

        return view
    }
}


