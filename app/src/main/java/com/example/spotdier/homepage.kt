package com.example.spotdier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class homepage : AppCompatActivity() {
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