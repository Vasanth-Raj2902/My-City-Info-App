package com.example.cityinformation.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.cityinformation.R

class StagesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stages)
        val state=intent.getStringExtra("state")
        val city=intent.getStringExtra("city")
        val int=Intent(this,LastActivity::class.java )
        int.putExtra("state",state)
        int.putExtra("city",city)
        findViewById<ImageView>(R.id.tor).setOnClickListener {
      int.putExtra("category","Tourist Places")
            startActivity(int)
        }
        findViewById<ImageView>(R.id.hotels).setOnClickListener {
            int.putExtra("category","Hotels")
            startActivity(int)
        }
        findViewById<ImageView>(R.id.busstans).setOnClickListener {
            int.putExtra("category","Bus Stands")
            startActivity(int)
        }
        findViewById<ImageView>(R.id.hospitals).setOnClickListener {
            int.putExtra("category","Hospitals")
            startActivity(int)
        }
        findViewById<ImageView>(R.id.police).setOnClickListener {
            int.putExtra("category","Police station")
            startActivity(int)
        }
        findViewById<ImageView>(R.id.college).setOnClickListener {
                        int.putExtra("category","College")
            startActivity(int)
        }
        findViewById<ImageView>(R.id.government).setOnClickListener {
                        int.putExtra("category","Government offices")
            startActivity(int)
        }



    }
}