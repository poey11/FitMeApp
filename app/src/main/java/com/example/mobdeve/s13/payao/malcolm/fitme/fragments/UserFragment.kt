package com.example.mobdeve.s13.payao.malcolm.fitme

/*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class UserFragment : Fragment() {

    private lateinit var accomplishmentsRecyclerView: RecyclerView
    private lateinit var nameTV: TextView
    private lateinit var sessionsValueTV: TextView
    private lateinit var volumeValueTV: TextView
    private lateinit var repsValueTV: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        // Initialize TextViews
        nameTV = view.findViewById(R.id.nameTV)
        sessionsValueTV = view.findViewById(R.id.sessionsValueTV)
        volumeValueTV = view.findViewById(R.id.volumeValueTV)
        repsValueTV = view.findViewById(R.id.repsValueTV)

        // Set text using UserDataHelper
        nameTV.text = UserDataHelper.getUserName()
        sessionsValueTV.text = UserDataHelper.getSessionValue().toString()
        volumeValueTV.text = UserDataHelper.getVolumeValue()
        repsValueTV.text = UserDataHelper.getRepsValue()

        // Initialize RecyclerView
        accomplishmentsRecyclerView = view.findViewById(R.id.accomplishmentsRecyclerView)
        accomplishmentsRecyclerView.layoutManager = LinearLayoutManager(context)

        // Initialize list of accomplishments using AccomplishmentDataHelper
        val accomplishments = AccomplishmentsDataHelper.initializeAccomplishments()

        // Create and set adapter
        val adapter = AccomplishmentsAdapter(accomplishments)
        accomplishmentsRecyclerView.adapter = adapter

        return view
    }
}*/


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserFragment : Fragment() {

    private lateinit var accomplishmentsRecyclerView: RecyclerView
    private lateinit var nameTV: TextView
    private lateinit var sessionsValueTV: TextView
    private lateinit var volumeValueTV: TextView
    private lateinit var repsValueTV: TextView
    private lateinit var userIconImageView: ImageView // Add this line

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        // Initialize TextViews
        nameTV = view.findViewById(R.id.nameTV)
        sessionsValueTV = view.findViewById(R.id.sessionsValueTV)
        volumeValueTV = view.findViewById(R.id.volumeValueTV)
        repsValueTV = view.findViewById(R.id.repsValueTV)


        // Initialize ImageView for user icon
        userIconImageView = view.findViewById(R.id.userIcon)

        // Set text using UserDataHelper
        nameTV.text = UserDataHelper.getUserName()
        sessionsValueTV.text = UserDataHelper.getSessionValue().toString()
        volumeValueTV.text = UserDataHelper.getVolumeValue()
        repsValueTV.text = UserDataHelper.getRepsValue()
        userIconImageView.setImageResource(UserDataHelper.getUserIcon()) // Set user icon

        // Initialize RecyclerView
        accomplishmentsRecyclerView = view.findViewById(R.id.accomplishmentsRecyclerView)
        accomplishmentsRecyclerView.layoutManager = LinearLayoutManager(context)

        // Initialize list of accomplishments using AccomplishmentDataHelper
        val accomplishments = AccomplishmentsDataHelper.initializeAccomplishments()

        // Create and set adapter
        val adapter = AccomplishmentsAdapter(accomplishments)
        accomplishmentsRecyclerView.adapter = adapter

        return view
    }
}

