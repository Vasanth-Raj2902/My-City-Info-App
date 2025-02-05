package com.example.cityinformation

import android.util.Base64
import com.example.cityinformation.Response.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Time
import java.util.concurrent.TimeUnit

object RetrofitIn {
private const val BASEURL="https://www.wizzie.online/CityInformation/"
private val okhttps=okhttp3.OkHttpClient().newBuilder()
        .connectTimeout(120,TimeUnit.SECONDS)
        .readTimeout(120,TimeUnit.SECONDS)
        .writeTimeout(120,TimeUnit.SECONDS)
        .build()



val instance : Api by lazy {
  val retro=  Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttps)
        .build()
    retro.create(Api::class.java)
}

}