package com.example.spotdier

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*

class add : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val db = Firebase.firestore


        val spinner: Spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        val location = findViewById<EditText>(R.id.location)
        val estimate = findViewById<EditText>(R.id.estimate)
        val description = findViewById<EditText>(R.id.description)

        val button = findViewById<Button>(R.id.button2)

        val TAG = "random"

        button.setOnClickListener {
            val locationText = location.text.toString()
            val estimateText = estimate.text.toString()
            val descriptionText = description.text.toString()
            val typeText = spinner.selectedItem.toString()
            val currentDate: String = DateTimeFormatter.ISO_INSTANT.format(Instant.now()).toString()

            val currentUid = FirebaseAuth.getInstance().currentUser!!.uid
            val report = hashMapOf(
                "uid" to currentUid,
                "description" to descriptionText,
                "estimate" to estimateText,
                "location" to locationText,
                "type" to typeText,
                "date" to currentDate
            )
            db.collection("reports")
                .add(report)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    Toast.makeText(this, "Your report has been recorded", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }

    }
}