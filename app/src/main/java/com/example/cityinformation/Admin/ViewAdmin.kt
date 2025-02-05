package com.example.cityinformation.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.cityinformation.LoginPage
import com.example.cityinformation.R

class ViewAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_admin)
        findViewById<CardView>(R.id.addplaces).setOnClickListener {
            startActivity(
                Intent(
                    this,
                    AddPlaces::class.java
                )
            )
        }
        findViewById<CardView>(R.id.card2).setOnClickListener {
            getSharedPreferences("user", MODE_PRIVATE).edit().clear().apply()
            startActivity(Intent(this,LoginPage::class.java))
            finishAffinity()
        }
    }
}