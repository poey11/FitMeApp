package com.example.mobdeve.s13.payao.malcolm.fitme.fragments



import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobdeve.s13.payao.malcolm.fitme.adapter.AccomplishmentsAdapter
import com.example.mobdeve.s13.payao.malcolm.fitme.database.AccomplishmentsDataHelper
import com.example.mobdeve.s13.payao.malcolm.fitme.database.UserDataHelper
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Settings
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.google.android.play.integrity.internal.f
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class UserFragment : Fragment() {

    private lateinit var accomplishmentsRecyclerView: RecyclerView
    private lateinit var firstNameTV: TextView
    private lateinit var lastNameTV: TextView
    private lateinit var sessionsValueTV: TextView
    private lateinit var volumeValueTV: TextView
    private lateinit var repsValueTV: TextView
    private lateinit var userIconImageView: ImageView
    private lateinit var settingsbtn: ImageButton
    private lateinit var userFirstName : String
    private lateinit var userLastName :String

    private  val auth:FirebaseAuth = FirebaseAuth.getInstance()
    private val db =  FirebaseFirestore.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        // Initialize TextViews
        firstNameTV = view.findViewById(R.id.firstNameTV)
        sessionsValueTV = view.findViewById(R.id.sessionsValueTV)
        volumeValueTV = view.findViewById(R.id.volumeValueTV)
        repsValueTV = view.findViewById(R.id.repsValueTV)

        // Initialize ImageView for user icon
        userIconImageView = view.findViewById(R.id.userIcon)

        val currentUser = auth.currentUser

        if (currentUser != null) {
            fetchUser(currentUser.uid)
        }

        // Get user data
        val userDataList = UserDataHelper.initializeUserData()
        val userData = userDataList[0]


        // Set TextViews to user data


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
            val intent = Intent(requireContext(), Settings::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun fetchUser(uID: String){
        db.collection("userInfo").whereEqualTo("UID",uID).get()
            .addOnSuccessListener{result ->
               if(!result.isEmpty){
                   val document = result.documents[0]
                   userFirstName = document.getString("fullName").toString()
                   sessionsValueTV.text = document.getLong("Sessions").toString()
                   volumeValueTV.text = document.getLong("Volume").toString()
                   repsValueTV.text = document.getLong("Reps").toString()
                   firstNameTV.text = userFirstName

                   val photoUrl = document.getString("photoUrl")
                   if (!photoUrl.isNullOrEmpty()) {
                       // Using Glide for loading the image from URL
                       Glide.with(this)
                           .load(photoUrl)
                           .placeholder(R.drawable.ic_baseline_user) // Placeholder image while loading
                           .error(R.drawable.ic_baseline_user) // Error image if loading fails
                           .into(userIconImageView)
                   }
               }

            }.addOnFailureListener{exception ->
                Log.e("WOAH", "Error getting user data $exception", exception)
            }
    }

}


