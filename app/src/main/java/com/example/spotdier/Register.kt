package com.example.spotdier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_loginpage.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.password
import kotlinx.android.synthetic.main.activity_register.username

class Register : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        //btn_signup.setOnClickListener(
        //signUpUser()

        val btn_login = findViewById<Button>(R.id.back_to_login)

        btn_login.setOnClickListener {
            val intent = Intent(this, loginpage::class.java)


            intent.putExtra("name", "nullText")

            startActivity(intent)
        }

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
                                val city = findViewById<EditText>(R.id.city)
                                val job = findViewById<EditText>(R.id.job)
                                val name = findViewById<EditText>(R.id.name)

                                val cityText = city.text.toString()
                                val jobText = job.text.toString()
                                val nameText = name.text.toString()

                                val intent = Intent(this, loginpage::class.java)

                                intent.putExtra("job", jobText)
                                intent.putExtra("name", nameText)
                                intent.putExtra("city", cityText)
                                intent.putExtra("reportScore", "0")
                                startActivity(intent)
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


