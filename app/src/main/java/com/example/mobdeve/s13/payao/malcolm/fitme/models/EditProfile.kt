package com.example.mobdeve.s13.payao.malcolm.fitme.models

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mobdeve.s13.payao.malcolm.fitme.MainActivity
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class EditProfile : AppCompatActivity() {

    private lateinit var firstNameInputET: EditText
    private lateinit var pictureLabel : TextView
    private lateinit var lastNameInputET: EditText
    private lateinit var ageInputET: EditText
    private lateinit var weightInputET: EditText
    private lateinit var heightInputET: EditText
    private lateinit var saveBtn: Button
    private lateinit var changePicBtn: Button
    private lateinit var backBtn: Button
    private val db =  FirebaseFirestore.getInstance()
    private  val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private  var firestore : FirebaseFirestore = FirebaseFirestore.getInstance()
    private val usersCollection = firestore.collection("userInfo")
    private var storage: FirebaseStorage = FirebaseStorage.getInstance()
    private var uploadedImageUri:Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editprofile_template)
        val currentUser = auth.currentUser
        // Initialize EditText fields
         pictureLabel = findViewById(R.id.pictureLabelTV)
        firstNameInputET = findViewById(R.id.firstNameInputET)
        lastNameInputET = findViewById(R.id.lastNameInputET)
        ageInputET = findViewById(R.id.ageInputET)
        weightInputET = findViewById(R.id.weightInputET)
        heightInputET = findViewById(R.id.heightInputET)

        changePicBtn = findViewById(R.id.changePicBtn)
        saveBtn = findViewById(R.id.saveBtn)
        backBtn = findViewById(R.id.backBtn)


        fetchUser()



        // Set click listener for Save button
        saveBtn.setOnClickListener {
            if (currentUser != null) {
                saveChanges(currentUser.uid)
            }

        }
        changePicBtn.setOnClickListener{
            selectImageFromGallery()
        }
        backBtn.setOnClickListener {
            finish()
        }
    }

    private fun selectImageFromGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, 150)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 150 && resultCode == RESULT_OK) {
            uploadedImageUri = data?.data
            pictureLabel.text = "Image selected"
        }
    }


    private fun saveChanges(user: String) {

        if(uploadedImageUri !=null){
            val storageRef = storage.reference
            val imageRef = storageRef.child("images/${uploadedImageUri?.lastPathSegment}")
            val uploadTask = imageRef.putFile(uploadedImageUri!!)
            uploadTask.addOnSuccessListener { taskSnapshot ->
                imageRef.downloadUrl.addOnSuccessListener { uri ->
                   val  downloadUrl = uri.toString()
                    saveUserData(user,downloadUrl)
                    Log.d("EditProfile", "Image uploaded successfully")
                }.addOnFailureListener {
                    Log.e("EditProfile", "Error getting download URL", it)
                }
            }.addOnFailureListener {
                Log.e("EditProfile", "Error uploading image", it)
            }
        }
        else{
            saveUserData(user,null)

        }

    }


    private fun fetchUser() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            db.collection("userInfo").whereEqualTo("UID", currentUser.uid).get()
                .addOnSuccessListener { result ->
                    if (!result.isEmpty) {
                        val document = result.documents[0]
                        val fullName = document.getString("fullName")
                        val age = document.getLong("Age")?.toInt()
                        val weight = document.getLong("Weight")?.toInt()
                        val height = document.getLong("Height")?.toInt()

                        // Set EditText fields if data is not null
                        fullName?.let {
                            val names = fullName.split(" ")
                            if (names.size >= 2) {
                                firstNameInputET.setText(names[0])
                                lastNameInputET.setText(names[1])
                            }
                        }
                        age?.let { ageInputET.setText(it.toString()) }
                        weight?.let { weightInputET.setText(it.toString()) }
                        height?.let { heightInputET.setText(it.toString()) }
                    }
                }.addOnFailureListener { exception ->
                    Log.e("WOAH", "Error getting user data $exception", exception)
                }
        }
    }


    private fun saveUserData(user: String, downloadUrl: String?) {
        val firstName = firstNameInputET.text.toString()
        val lastName = lastNameInputET.text.toString()

        val ageText = ageInputET.text.toString()
        val weightText = weightInputET.text.toString()
        val heightText = heightInputET.text.toString()

        if (firstName.isEmpty() || lastName.isEmpty() || ageText.isEmpty() || weightText.isEmpty() || heightText.isEmpty()) {
            Toast.makeText(this, "Please fill up all the fields", Toast.LENGTH_SHORT).show()
        } else {
            try {
                val age = ageText.toInt()
                val weight = weightText.toInt()
                val height = heightText.toInt()

                val fullName = "$firstName $lastName"

                val userMap = hashMapOf(
                    "fullName" to fullName,
                    "Age" to age,
                    "Weight" to weight,
                    "Height" to height
                )

                // Add download URL to userMap if available
                if (downloadUrl != null) {
                    userMap["photoUrl"] = downloadUrl
                }

                usersCollection.document(user).update(userMap as Map<String, Any>)
                    .addOnSuccessListener {
                        Log.d("EditProfile", "DocumentSnapshot successfully updated!")
                    }
                    .addOnFailureListener { e -> Log.w("EditProfile", "Error updating document", e) }
                Toast.makeText(this, "Information saved", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Invalid input for age, weight, or height", Toast.LENGTH_SHORT).show()
            }
        }
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}