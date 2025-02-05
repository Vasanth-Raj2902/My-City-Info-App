package com.example.cityinformation.User

import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cityinformation.Adapter2
import com.example.cityinformation.Model.State
import com.example.cityinformation.R
import com.example.cityinformation.Response.CtiyResponse
import com.example.cityinformation.Response.Place3
import com.example.cityinformation.Response.PlacesResponse3
import com.example.cityinformation.RetrofitIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

abstract class CityView : AppCompatActivity(),clcick {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_view)
        val city=intent.getStringExtra("state")
        val cyle=findViewById<RecyclerView>(R.id.myview)
        cyle.layoutManager=GridLayoutManager(this,2)

        CoroutineScope(IO).launch {
            RetrofitIn.instance.getcity(city!!).enqueue(object :Callback<CtiyResponse>{
                override fun onResponse(
                    call: Call<CtiyResponse>,
                    response: Response<CtiyResponse>
                ) {
                    cyle.adapter= Adapter2(this@CityView,response.body()!!.place,this@CityView)
                }

                override fun onFailure(call: Call<CtiyResponse>, t: Throwable) {
                    Toast.makeText(this@CityView, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
        val map = mapOf("Alice" to 20, "Tom" to 13, "Bob" to 18)
        val adults = map.mapNotNull { (name, age) ->
            name.takeIf { age >= 18 } }
    }

    override fun click(g: Place3) {
        val int=Intent(this,StagesActivity::class.java)
        int.putExtra("state",intent.getStringExtra("state"))
        int.putExtra("city",g.city)
        startActivity(int)

    }



}