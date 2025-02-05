package com.example.cityinformation.User

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cityinformation.FinalAdapter
import com.example.cityinformation.R
import com.example.cityinformation.Response.PlacesResponse3
import com.example.cityinformation.RetrofitIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last)
    val cycle2=findViewById<RecyclerView>(R.id.cycle2)
        cycle2.layoutManager=LinearLayoutManager(this)
        val p=ProgressDialog(this)

        val state=intent.getStringExtra("state")
        val city=intent.getStringExtra("city")
        val catgory=intent.getStringExtra("category")

        p.setCancelable(false)
        p.show()
        CoroutineScope(IO).launch {
            RetrofitIn.instance.getdata("$state","$city","$catgory").enqueue(
                object :Callback<PlacesResponse3>{
                    override fun onResponse(call: Call<PlacesResponse3>, response: Response<PlacesResponse3>) {
                        cycle2.adapter= FinalAdapter(this@LastActivity,response.body()!!.place)
                        p.dismiss()
                    }

                    override fun onFailure(call: Call<PlacesResponse3>, t: Throwable) {
                        p.dismiss()
                        Toast.makeText(this@LastActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        }


    }
}