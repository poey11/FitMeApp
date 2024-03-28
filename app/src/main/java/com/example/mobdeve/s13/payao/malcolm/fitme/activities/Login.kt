package com.example.mobdeve.s13.payao.malcolm.fitme.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.mobdeve.s13.payao.malcolm.fitme.MainActivity
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.example.mobdeve.s13.payao.malcolm.fitme.processes.LoginBackend
import com.example.mobdeve.s13.payao.malcolm.fitme.processes.LoginListener
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.example.mobdeve.s13.payao.malcolm.fitme.models.User
class Login: AppCompatActivity() , LoginListener {

    private lateinit var loginField: EditText
    private lateinit var passField: EditText
    private lateinit var googleBtn: ImageButton
    private  var firestore : FirebaseFirestore = FirebaseFirestore.getInstance()
    private val usersCollection = firestore.collection("userInfo")
    private lateinit var auth:FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)
        googleBtn = findViewById(R.id.googleBtn)
        loginField= findViewById(R.id.emailAddInputET)
        passField= findViewById(R.id.passInputET)

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
          requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

       mGoogleSignInClient = GoogleSignIn.getClient(this,gso)
        googleBtn.setOnClickListener{
            signInWithGoogle()
        }


        showLogin()
    }

    private  fun showLogin(){

        val loginBtn: Button = findViewById(R.id.loginBtn)
        val forgotBtn: Button = findViewById(R.id.forgotPassBtn)
        val registerBtn: Button = findViewById(R.id.signUpHereBtn)
        loginBtn.setOnClickListener{
            val email: String = loginField.text.toString()
            val pass: String = passField.text.toString()
            if( email.isEmpty() && pass.isEmpty()) {
                Toast.makeText(this,"Please fill up both email and password", Toast.LENGTH_SHORT).show()
            }
            else if (pass.isEmpty()){
                Toast.makeText(this,"Please fill up password", Toast.LENGTH_SHORT).show()

            }
            else if (email.isEmpty()){
                Toast.makeText(this,"Please fill up email", Toast.LENGTH_SHORT).show()

            }
            else{
                LoginBackend.login(email, pass, this)
            }
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
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)

    }

    override fun onLoginFailure(errorMessage: String) {
        Toast.makeText(this,errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun signInWithGoogle() {
        val signInIntent = mGoogleSignInClient.signInIntent
        signInLauncher.launch(signInIntent)
    }

    private  var signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
        if(result.resultCode == RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(task)
        }
    }

    override fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = task.result
            if (account != null) {
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                auth.signInWithCredential(credential).addOnCompleteListener { signInTask ->
                    if(signInTask.isSuccessful) {
                        val currentUser = auth.currentUser
                        if(currentUser != null) {
                            checkIfUserExists(currentUser.uid){exists ->
                                if(exists){
                                    startActivity(Intent(this, MainActivity::class.java))
                                    finish()
                                }
                                else{
                                    val userData = hashMapOf(
                                        "fName" to currentUser.displayName!!.split(" ")[0],
                                        "lName" to currentUser.displayName!!.split(" ")[0],
                                        "UID" to currentUser.uid
                                    )
                                    usersCollection.document(currentUser.uid).set(userData).addOnSuccessListener {
                                        startActivity(Intent(this, MainActivity::class.java))
                                        finish()
                                    }.addOnFailureListener{ e->
                                        Toast.makeText(this,"Failed to add to the DB ${e.message}",Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }

                        }

                    }
                }
            }
        } catch (e: ApiException) {
            Toast.makeText(this, "Sign-in failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkIfUserExists(userId: String, callback : (Boolean) -> Unit){
        usersCollection.document(userId).get().addOnSuccessListener{documentSnapshot ->
            val exist = documentSnapshot.exists()
            callback(exist)
        }
            .addOnFailureListener { e ->
                Toast.makeText(
                    this,
                    "Failed to check user existence: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
                callback(false)
            }

    }

}