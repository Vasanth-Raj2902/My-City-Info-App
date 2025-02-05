package com.example.cityinformation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.cityinformation.Admin.ViewAdmin
import com.example.cityinformation.User.CitySelecting

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    val user=getSharedPreferences("user", MODE_PRIVATE).getString("name","")
        val image=findViewById<ImageView>(R.id.startic)
        image.alpha=0f
        image.animate().setDuration(500).alpha(1f).withEndAction {
            finishAffinity()
            if(user=="admin"){
                startActivity(Intent(this, ViewAdmin::class.java))

            }else if(user!!.isNotEmpty()){
                startActivity(Intent(this,CitySelecting::class.java))
            }else {
                startActivity(Intent(this, LoginPage::class.java))
            }}.withStartAction {
            overridePendingTransition(androidx.appcompat.R.anim.abc_fade_in, androidx.appcompat.R.anim.abc_fade_out)
        }
    }
}