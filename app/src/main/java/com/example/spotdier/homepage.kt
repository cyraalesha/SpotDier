package com.example.spotdier

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.format.DateTimeFormatter

class homepage : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val button = findViewById<ImageButton>(R.id.location_button)
        button.setOnClickListener {
            val intent = Intent(this, location::class.java)
            startActivity(intent)
        }

        val buttonn = findViewById<ImageButton>(R.id.add_button)
        buttonn.setOnClickListener {
            val intent = Intent(this, add::class.java)
            startActivity(intent)
        }



        val butttton = findViewById<ImageButton>(R.id.person_button)
        butttton.setOnClickListener {

            val intent = Intent(this, person::class.java)
            startActivity(intent)
        }
    }
}