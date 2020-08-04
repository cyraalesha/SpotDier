package com.example.spotdier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class specificArea : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_area)
        val db = Firebase.firestore

        val location = intent.extras!!.getString("location")
        val time = intent.extras!!.getString("time")

        var uid: String;

        db.collection("reports")
            .whereEqualTo("location", location)
            .whereEqualTo("date", time)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val type = findViewById<TextView>(R.id.type)
                    val location = findViewById<TextView>(R.id.location)
                    val estimate = findViewById<TextView>(R.id.estimate)

                    type.text = "${document.data["type"]}"
                    location.text = "${document.data["location"]}"
                    estimate.text = "${document.data["estimate"]}"

                    uid = "${document.data["uid"]}"

                }
            }






    }
}