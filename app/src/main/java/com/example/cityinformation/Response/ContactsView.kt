package com.example.cityinformation.Response

import com.google.gson.annotations.SerializedName
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

data class ContactsView (
    @SerializedName("id"            ) var id           : String? = null,
    @SerializedName("userid"        ) var userid       : String? = null,
    @SerializedName("name"          ) var name         : String? = null,
    @SerializedName("mobile_number" ) var mobileNumber : String? = null
)
