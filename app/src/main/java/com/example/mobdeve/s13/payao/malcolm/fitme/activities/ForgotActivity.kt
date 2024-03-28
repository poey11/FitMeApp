package com.example.mobdeve.s13.payao.malcolm.fitme.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobdeve.s13.payao.malcolm.fitme.R
import com.google.firebase.auth.FirebaseAuth

class ForgotActivity:AppCompatActivity() {
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var sendBtn: Button
    private lateinit var emailField: EditText
    private lateinit var loginBtn : TextView
    private lateinit var regBtn : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_forgot)
        sendBtn = findViewById(R.id.sendBtn)

        loginBtn = findViewById(R.id.textView8)
        regBtn = findViewById(R.id.textView9)
        emailField = findViewById(R.id.firstNameInputET)

        sendBtn.setOnClickListener{
            val emailString:String = emailField.text.toString()
            restPassword(emailString)
        }

        loginBtn.setOnClickListener{
            finish()
        }

        regBtn.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private  fun restPassword(email:String){
        if(email.isEmpty()){
            Toast.makeText(this,"Please fill up Email", Toast.LENGTH_SHORT).show()
        }else{
            auth.sendPasswordResetEmail(email).addOnCompleteListener{task ->
                if(task.isSuccessful){
                    Toast.makeText(this,"Password reset email sent to $email", Toast.LENGTH_SHORT).show()
                }
                finish()
            }
        }
    }

}