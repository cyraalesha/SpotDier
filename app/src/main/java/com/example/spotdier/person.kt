package com.example.spotdier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_add.*

class person : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

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