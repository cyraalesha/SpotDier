package com.example.spotdier

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        handler = Handler()
        handler.postDelayed({

            val intent =
                Intent(this, loginpage::class.java)
            startActivity(intent)
            finish()


        }, 3000)

    }
}