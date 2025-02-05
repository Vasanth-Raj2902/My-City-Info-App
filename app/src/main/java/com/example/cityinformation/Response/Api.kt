package com.example.cityinformation.Response

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @FormUrlEncoded
    @POST("users.php")
    fun creatingusers(
        @Field("name")name:String,
        @Field("mobile")mobile:String,
        @Field("mail")mail:String,
        @Field("password")password:String
    ):Call<CommonResponse>
@FormUrlEncoded
@POST("users.php")
    fun login(
    @Field("mail")mail:String,
    @Field("password")password:String,
    @Field("condition")condition:String
):Call<UserResponse>
    @FormUrlEncoded
    @POST("addplaces.php")
    fun addplaces(
        @Field("type")type:String,
        @Field("city")city:String,
        @Field("state")state:String,
        @Field("citypath")citypath:String,
        @Field("statepath")statepath:String,
        @Field("placediscription")placediscription:String,
        @Field("placename")placename:String,
        @Field("placepath")placepath:String
    ):Call<CommonResponse>

    @FormUrlEncoded
    @POST("getplaces.php")
    fun getstate(
@Field("condition")condition:String
    ):Call<PlacesResponse2>

    @FormUrlEncoded
    @POST("getplaces.php")
    fun getcity(
        @Field("condition")condition:String
            ):Call<CtiyResponse>

    @FormUrlEncoded
    @POST("getdetailsplaces.php")
    fun getdata(
        @Field("state")state:String,
        @Field("city")city:String,
        @Field("catogry")catogry:String
    ):Call<PlacesResponse3>

    @FormUrlEncoded
    @POST("addcontacts.php")
    fun addcontact(
        @Field("userid")userid:String,
    @Field("name")name:String,
    @Field("mobile_number")mobile_number:String,
    ):Call<CommonResponse>

    @GET("getcontacts.php")
     fun getcontacts(
        @Query("id") id: String):Call<ContactResponse>

     @GET("delete.php")
    fun deletefun(
        @Query("id")id:String
    ):Call<CommonResponse>


}