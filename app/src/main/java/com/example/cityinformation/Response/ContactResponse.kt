package com.example.cityinformation.Response

import com.google.gson.annotations.SerializedName
import java.lang.Error

data class ContactResponse(
    @SerializedName("error"   ) var error   : Boolean?         = null,
    @SerializedName("message" ) var message : String?          = null,
    @SerializedName("place"   ) var place   : ArrayList<ContactsView> = arrayListOf()
)