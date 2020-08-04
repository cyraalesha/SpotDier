package com.example.spotdier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_loginpage.*

class loginpage : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginpage)
// ...
// Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        btn_sign_up.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
            finish()
        }

        btn_log_in.setOnClickListener {
            dologin()
        }

    }

    private fun dologin() {
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
        auth.signInWithEmailAndPassword(username.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext, "Oh no, login failed",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                    // ...
                }

                // ...
            }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            if (currentUser.isEmailVerified) {
                startActivity(Intent(this, homepage::class.java))
            } else {
                Toast.makeText(
                    baseContext, "Please verify your email",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }


    }
}