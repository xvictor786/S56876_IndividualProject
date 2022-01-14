package com.example.picturematch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class startPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_page)

        val actionBar = supportActionBar
        actionBar!!.title="PICTURE MATCH" //title appbar

        //button untuk start game
        var buttonStart = findViewById<Button>(R.id.btnStart)
        buttonStart.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //button untuk exit game
        var buttonExit = findViewById<Button>(R.id.btnExit)
        buttonExit.setOnClickListener{
            finish()
            System.exit(0)
        }
    }
}