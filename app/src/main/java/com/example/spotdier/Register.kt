package com.example.spotdier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        //btn_signup.setOnClickListener(
        //signUpUser()
        btn_signup.setOnClickListener {
            signUpUser()
        }

    }

    private fun signUpUser(){
        //if (!Patterns.EMAIL_ADDRESS.matcher(username.toString()).matches()) {
        //username.error = "Please enter a valid email"
        //username.requestFocus()
        //return
        //}

        if (username.text.toString().isEmpty()) {
            username.error = "Please enter email"
            username.requestFocus()
            return
        }

        if (password.text.toString().isEmpty()) {
            password.error = "Please enter password"
            password.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(username.text.toString(), password.text.toString())
            .addOnCompleteListener(this)
            { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user!!.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this, loginpage::class.java))
                                finish()
                            }
                        }

                } else {
                    Toast.makeText(baseContext, "Sign Up failed. Try again after some time.",
                        Toast.LENGTH_SHORT).show()
                }
            }

        // ...
    }


}


