package com.example.cityinformation

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.cityinformation.Response.CommonResponse
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val name=findViewById<TextInputEditText>(R.id.name);
        val mobile=findViewById<TextInputEditText>(R.id.mobile);
        val mail=findViewById<TextInputEditText>(R.id.mail);
        val password=findViewById<TextInputEditText>(R.id.password);

        findViewById<Button>(R.id.btn).setOnClickListener {
            val name1=name.text.toString();
            val mobile1=mobile.text.toString();
            val mail1=mail.text.toString();
            val password1=password.text.toString();
            if(
                name1.isEmpty()){
                it.toast("Please Enter Your name")
            }else if(mobile1.isEmpty()){
                it.toast("Please Enter Your mobile")
            }else if(mail1.isEmpty()){
it.toast("Please Enter Your Mail")
            }else if(password1.isEmpty()){
it.toast("Please Enter Your password")
            }else{
                val p=ProgressDialog(this).apply {
                    setTitle("Please Wait for minute \uD83D\uDE0A")
                    setCancelable(false)
                    show()
                }
CoroutineScope(IO).launch {
    RetrofitIn.instance.creatingusers(name1,mobile1,mail1,password1).enqueue(object :Callback<CommonResponse>{
        override fun onResponse(call: Call<CommonResponse>, response: Response<CommonResponse>) {
        val k=response.body()!!.message
            it.toast(k)
            p.dismiss()
            if(k=="Hi!! '$name1' Welcome to CityInformation Application"){
                startActivity(Intent(this@SignUp,LoginPage::class.java))
                finishAffinity()
            }
        }

        override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
        it.toast("error -> ${t.message}")
            p.dismiss()
        }
    })
}
            }
        }


    }
}