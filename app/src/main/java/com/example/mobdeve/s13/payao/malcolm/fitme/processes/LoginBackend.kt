package com.example.mobdeve.s13.payao.malcolm.fitme.processes

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

interface LoginListener{
    fun onLoginSuccess()
    fun onLoginFailure(errorMessage:String)

    fun signInWithGoogle()
    fun handleSignInResult(task: Task<GoogleSignInAccount>)

}
object LoginBackend {


    fun login(email: String, password:String, listener: LoginListener){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                listener.onLoginSuccess()
            }
            .addOnFailureListener{exception->
                listener.onLoginFailure(exception.message ?: "Login Failed")
            }
    }



}