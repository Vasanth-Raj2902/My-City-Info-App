package com.example.cityinformation.User

import android.telecom.CallScreeningService.CallResponse
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cityinformation.Response.ContactResponse
import com.example.cityinformation.Response.ContactsView
import com.example.cityinformation.RetrofitIn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel:ViewModel() {
    val mutable=MutableLiveData(ArrayList<ContactsView>())
    private val retrofit=RetrofitIn.instance


    fun getdata(id:String){
        retrofit.getcontacts(id = id).enqueue(object :Callback<ContactResponse>{
            override fun onResponse(
                call: Call<ContactResponse>,
                response: Response<ContactResponse>
            ) {
             response.body()?.let {
                 mutable.value=it.place
             }
            }

            override fun onFailure(call: Call<ContactResponse>, t: Throwable) {
            Log.i("viewModel teext","${t.message}")

            }

        })
    }


}