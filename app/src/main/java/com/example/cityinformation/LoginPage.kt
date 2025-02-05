package com.example.cityinformation

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.cityinformation.Admin.ViewAdmin
import com.example.cityinformation.Response.UserResponse
import com.example.cityinformation.User.CitySelecting
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        val mail=findViewById<TextInputEditText>(R.id.lmail)
        val pass=findViewById<TextInputEditText>(R.id.pass)
        val signup=findViewById<TextView>(R.id.signup)
        signup.setOnClickListener {
            startActivity(Intent(this,SignUp::class.java))
        }
        findViewById<TextView>(R.id.logins).setOnClickListener {
            val mail1=mail.text.toString()
            val pass1=pass.text.toString()
            if (
                mail1.isEmpty()
                    ){
                it.toast("Please Enter Your a Mail")
            }else if (pass1.isEmpty()){
                it.toast("Please Enter Your password")
            }else if(mail1=="admin"&&pass1=="admin"){
                getSharedPreferences("user", MODE_PRIVATE).edit().putString("name","admin").apply()
                startActivity(Intent(this, ViewAdmin::class.java))
                finishAffinity()
                }else{
                val p=ProgressDialog(this).apply {
                setTitle("\uD83D\uDE0A->Loading.....")
                    setCancelable(false)
                    show()
                }
                CoroutineScope(IO).launch {
                    RetrofitIn.instance.login(mail1,pass1,"login").enqueue(object :Callback<UserResponse>{
                        override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                        val res=response.body()
                            p.dismiss()
                            if(res!!.data.isEmpty()){
                                it.toast(res.message)
                            }else{
                                val data=res.data[0]
                                getSharedPreferences("user", MODE_PRIVATE).edit().apply {
                                    putString("id",data.id.toString())
                                    putString("name",data.name)
                                    putString("mail",data.mail)
                                    putString("password",data.password)
                                    putString("mobile",data.mobile)
                                    apply()
                                }
                                startActivity(Intent(this@LoginPage,CitySelecting::class.java))
                                finishAffinity()
                            }
                        }
                        override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                       it.toast("${t.message}")
                        p.dismiss()
                        }
                    }) }
            }

        }

    }
}