package com.example.cityinformation.User

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cityinformation.Adapter
import com.example.cityinformation.LoginPage
import com.example.cityinformation.R
import com.example.cityinformation.Response.PlacesResponse2
import com.example.cityinformation.Response.Response2
import com.example.cityinformation.RetrofitIn
import com.example.cityinformation.databinding.ActivityCitySelectingBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class CitySelecting : AppCompatActivity() {
val uri=ArrayList<String>()
    val p by lazy {
        ProgressDialog(this).apply {
            setTitle("Fetching Data")
            setCancelable(false)
            show()
        }
    }



    private lateinit var bind:ActivityCitySelectingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind=ActivityCitySelectingBinding.inflate(layoutInflater)
        setContentView(bind.root)


        bind.logout.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this,LoginPage::class.java))
            getSharedPreferences("user", MODE_PRIVATE).edit().clear().apply()
        }
        bind.call.setOnClickListener {
            startActivity(Intent(this,ContactingActivity::class.java))
        }

        val k=bind.cycle.apply {
            isNestedScrollingEnabled=true
            layoutManager=GridLayoutManager(this@CitySelecting,2)

        }



        CoroutineScope(IO).launch {
            RetrofitIn.instance.getstate("State").enqueue(object :Callback<PlacesResponse2> {
                override fun onResponse(call: Call<PlacesResponse2>, response: Response<PlacesResponse2>) {

k.adapter=Adapter(this@CitySelecting,response.body()!!.place)
                    p.dismiss()
                    /*Toast.makeText(this@CitySelecting, "${response.body()!!.place}"
                         *//*   "${response.body()!!.place}"*//*, Toast.LENGTH_SHORT).show()*/

                }
                override fun onFailure(call: Call<PlacesResponse2>, t: Throwable) {
                    Toast.makeText(this@CitySelecting, "${t.message}", Toast.LENGTH_SHORT).show()
                    p.dismiss()
                }
            })
        }

    }



}

