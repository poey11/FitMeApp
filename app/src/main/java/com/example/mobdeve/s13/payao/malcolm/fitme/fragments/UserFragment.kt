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
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Settings
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.models.Accomplishment
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
            fetchAccomplishments(currentUser.uid)
        }




        accomplishmentsRecyclerView = view.findViewById(R.id.accomplishmentsRecyclerView)
        accomplishmentsRecyclerView.layoutManager = LinearLayoutManager(context)



        settingsbtn = view.findViewById(R.id.settingsbtn)
        settingsbtn.setOnClickListener {
            val intent = Intent(requireContext(), Settings::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun fetchUser(uID: String){

        db.collection("userInfo").whereEqualTo("UID", uID).get()
            .addOnSuccessListener { userInfoResult ->
                if (!userInfoResult.isEmpty) {
                    val document = userInfoResult.documents[0]
                    userFirstName = document.getString("fullName").toString()
                    firstNameTV.text = userFirstName

                    val photoUrl = document.getString("photoUrl")
                    if (!photoUrl.isNullOrEmpty()) {
                        Glide.with(this)
                            .load(photoUrl)
                            .placeholder(R.drawable.ic_baseline_user)
                            .error(R.drawable.ic_baseline_user)
                            .into(userIconImageView)
                    }
                }
            }.addOnFailureListener { exception ->
                Log.e("WOAH", "Error getting user data $exception", exception)
            }


        db.collection("userExercise").document(uID).collection("listOfPastExercise").get()
            .addOnSuccessListener { exerciseResult ->
                var totalSets = 0L
                var totalReps = 0L
                var totalKg = 0L

                for (document in exerciseResult.documents) {
                    val sets = document.getLong("sets") ?: 0L
                    val reps = document.getLong("reps") ?: 0L
                    val kg = document.getLong("kg") ?: 0L

                    totalSets += sets
                    totalReps += reps
                    totalKg += kg
                }


                sessionsValueTV.text = totalSets.toString()
                volumeValueTV.text = totalKg.toString()
                repsValueTV.text = totalReps.toString()

            }.addOnFailureListener { exception ->
                Log.e("WOAH", "Error getting user exercise data $exception", exception)
            }
    }


    private fun fetchAccomplishments(uID: String) {
        db.collection("userExercise").document(uID).collection("listOfPastExercise").get()
            .addOnSuccessListener { result ->
                val accomplishmentsList = mutableListOf<Accomplishment>()


                val setsMap = mutableMapOf<String, Long>()
                for (document in result.documents) {
                    val title = document.getString("exTitle")
                    val sets = document.getLong("sets")

                    if (!title.isNullOrEmpty() && sets != null) {
                        setsMap[title] = (setsMap[title] ?: 0) + sets
                    }
                }


                for ((title, totalSets) in setsMap) {
                    val badgeImgResId = when (totalSets) {
                        in 10..29 -> R.drawable.bronze
                        in 30..49 -> R.drawable.silver
                        in 50..99 -> R.drawable.gold
                        else -> if (totalSets >= 100) R.drawable.plat else null
                    }
                    if (badgeImgResId != null) {
                        accomplishmentsList.add(Accomplishment(title, badgeImgResId))
                    }
                }

                // Set up RecyclerView adapter
                val adapter = AccomplishmentsAdapter(accomplishmentsList, requireContext())
                accomplishmentsRecyclerView.adapter = adapter
            }.addOnFailureListener { exception ->
                Log.e("WOAH", "Error getting user accomplishments $exception", exception)
            }
    }

}



