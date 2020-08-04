package com.example.spotdier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_loginpage.*


class loginpage : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginpage)
        val db = Firebase.firestore
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
        val db = Firebase.firestore
        if (currentUser != null) {
            if (currentUser.isEmailVerified) {
                val currentUid = FirebaseAuth.getInstance().currentUser!!.uid
                val name = intent.extras?.getString("name")
                name?.let {
                    if (name == "nullText") {
                        startActivity(Intent(this, homepage::class.java))
                    } else {

                        val job = intent.extras!!.getString("job")
                        val city = intent.extras!!.getString("city")
                        val user = hashMapOf(
                            "uid" to currentUid,
                            "name" to name,
                            "job" to job,
                            "city" to city,
                            "reportScore" to 0
                        )
                        val TAG = "random"
                        db.collection("users")
                            .add(user)
                            .addOnSuccessListener { documentReference ->
                                Log.d(
                                    TAG,
                                    "DocumentSnapshot added with ID: ${documentReference.id}"
                                )
                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error adding document", e)
                            }
                    }
                }
                        startActivity(Intent(this, homepage::class.java))
//                startActivity(Intent(this, homepage::class.java))

//                if (name == "nullText") {
//                    startActivity(Intent(this, homepage::class.java))
//                } else {
//
//                    val job = intent.extras!!.getString("job")
//                    val city = intent.extras!!.getString("city")
//                    val user = hashMapOf(
//                        "uid" to currentUid,
//                        "name" to name,
//                        "job" to job,
//                        "city" to city,
//                        "reportScore" to 0
//                    )
//                    val TAG = "random"
//                    db.collection("users")
//                        .add(user)
//                        .addOnSuccessListener { documentReference ->
//                            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//                        }
//                        .addOnFailureListener { e ->
//                            Log.w(TAG, "Error adding document", e)
//                        }
//                    startActivity(Intent(this, homepage::class.java))

            } else {
                Toast.makeText(
                    baseContext, "Please verify your email",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }


    }
}