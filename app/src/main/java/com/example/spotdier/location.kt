package com.example.spotdier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.HashMap

class location : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        val db = Firebase.firestore
        val listView = findViewById<ListView>(R.id.listView)
        val list = mutableListOf<String>()
        var reports = mutableListOf<Any>()
        val addresses = mutableListOf<String>()
        var map: MutableMap<String, String> = HashMap()

        db.collection("reports")
            .get()
            .addOnSuccessListener {documents ->
                for (document in documents) {
                    Log.d("random", document.toString())
//                    var report  = Report();
                    map["${document.data["date"]}"] = "${document.data["location"]}"


                }
                val sortedMap: MutableMap<String, String> = map.toSortedMap(reverseOrder())
                for ((key, value) in sortedMap) {
                    addresses.add("\n$value\n\n\t\t\t\t$key")
                }
                Log.d("random", addresses.toString())
                val adapter: ArrayAdapter<String> = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    addresses
                )

                listView.adapter = adapter

                listView.setOnItemClickListener { parent, view, position, id ->
                    val element = adapter.getItem(position) // The item that was clicked
                    val location = element.toString().reversed().substring(24).reversed().trim()
                    val time = element.toString().reversed().substring(0, 24).reversed().trim()

                    val intent = Intent(this, specificArea::class.java)

                    intent.putExtra("location", location)
                    intent.putExtra("time", time)
                    startActivity(intent)
                }

            }


//        for (report in reports) {
//            addresses.add(report.address.toString())
//        }

        Log.d("random", reports.toString())





        val point = findViewById<ImageButton>(R.id.pointlogousers)
//        point.setOnClickListener {
//            val intent = Intent(this, location::class.java)
//            startActivity(intent)
//        }

//        val addbtn = findViewById<ImageButton>(R.id.addlogousers)
//        addbtn.setOnClickListener {
//            val intent = Intent(this, add::class.java)
//            startActivity(intent)
//        }
//
//        val userbtn = findViewById<ImageButton>(R.id.userlogousers)
//        userbtn.setOnClickListener {
//            val intent = Intent(this, person::class.java)
//            startActivity(intent)
//        }

    }
}