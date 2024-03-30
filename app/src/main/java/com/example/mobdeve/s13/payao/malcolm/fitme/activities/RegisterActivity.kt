package com.example.mobdeve.s13.payao.malcolm.fitme.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobdeve.s13.payao.malcolm.fitme.MainActivity
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth


class RegisterActivity: AppCompatActivity() {
    private lateinit var signUpBtn:Button

    private lateinit var emailField:EditText
    private lateinit var cPassField:EditText
    private lateinit var passField:EditText
    private lateinit var fNameField:EditText
    private lateinit var lNameField:EditText

    private val rAuth : FirebaseAuth = FirebaseAuth.getInstance()
    private  var firestore : FirebaseFirestore = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)

        emailField = findViewById(R.id.emailAddInputET)
        cPassField = findViewById(R.id.confirmPassInputET)
        passField =findViewById(R.id.passInputET)
        fNameField =findViewById(R.id.firstNameInputET)
        lNameField =findViewById(R.id.lastNameInputET)
        signUpBtn = findViewById(R.id.signUpBtn)


        signUpBtn.setOnClickListener{
            val email: String = emailField.text.toString()
            val pass:String = passField.text.toString()
            val cPass:String = cPassField.text.toString()
            val fName:String = fNameField.text.toString()
            val lName:String = lNameField.text.toString()

            if( email.isEmpty() && cPass.isEmpty() &&  pass.isEmpty() && fName.isEmpty() && lName.isEmpty() ) {
                Toast.makeText(this,"Please fill up all the fields", Toast.LENGTH_SHORT).show()
            }
            else if (fName.isEmpty()){
                Toast.makeText(this,"Please fill up First Name", Toast.LENGTH_SHORT).show()

            }
            else if (lName.isEmpty()){
                Toast.makeText(this,"Please fill up Last Name", Toast.LENGTH_SHORT).show()

            }
            else if (email.isEmpty()){
                Toast.makeText(this,"Please fill up Email", Toast.LENGTH_SHORT).show()

            }
            else if (pass.isEmpty()){
                Toast.makeText(this,"Please fill up Password", Toast.LENGTH_SHORT).show()

            }
            else if (cPass.isEmpty()){
                Toast.makeText(this,"Please fill up Confirm Password", Toast.LENGTH_SHORT).show()

            }
            else{
                if(cPass != pass){
                    Toast.makeText(this,"The passwords does not match", Toast.LENGTH_SHORT).show()
                }
                else{
                    registerUser(email,pass,fName,lName)
                }
            }
        }

        val signBtn: Button = findViewById(R.id.signInHereBtn)

        signBtn.setOnClickListener{
            finish()
        }
    }

    private fun registerUser(email:String,pass:String,fName:String,lName:String){
        rAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this){task ->
            if(task.isSuccessful) {
                val user = rAuth.currentUser
                if (user != null){
                    val userDoc = firestore.collection("userInfo").document(user.uid)
                    val userData = hashMapOf(
                        "fullName" to "$fName $lName",
                        "Weight" to 0,
                        "Height" to 0,
                        "Sessions" to 0,
                        "Volume" to 0,
                        "Reps" to 0,
                        "UID" to user.uid,
                        "Age" to 0,
                        "photoUrl" to ""
                    )
                    userDoc.set(userData).addOnSuccessListener {
                        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                    }
                        .addOnFailureListener{e ->
                            Toast.makeText(this, "Failed to add user data to Firestore: ${e.message}", Toast.LENGTH_SHORT).show()
                        }

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            else{
                Toast.makeText(this, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
            }

        }
    }
}