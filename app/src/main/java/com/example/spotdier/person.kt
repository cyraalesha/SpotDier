package com.example.spotdier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add.*

class person : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        val db = Firebase.firestore

        val currentUid = FirebaseAuth.getInstance().currentUser!!.uid


        db.collection("users")
            .whereEqualTo("uid", currentUid)
            .get()
            .addOnSuccessListener {documents ->
                for (document in documents) {
                    val name = findViewById<TextView>(R.id.fullname)
                    val job = findViewById<TextView>(R.id.job)
                    val city = findViewById<TextView>(R.id.city)

                    city.text = "${document.data["city"]}"
                    name.text = "${document.data["name"]}"
                    job.text = "${document.data["job"]}"

                }

            }

        val point = findViewById<ImageButton>(R.id.pointlogousers)
        point.setOnClickListener {
            val intent = Intent(this, location::class.java)
            startActivity(intent)
        }

        val addbtn = findViewById<ImageButton>(R.id.addlogousers)
        addbtn.setOnClickListener {
            val intent = Intent(this, add::class.java)
            startActivity(intent)
        }

        val userbtn = findViewById<ImageButton>(R.id.userlogousers)
        userbtn.setOnClickListener {
            val intent = Intent(this, person::class.java)
            startActivity(intent)
        }



    }
}