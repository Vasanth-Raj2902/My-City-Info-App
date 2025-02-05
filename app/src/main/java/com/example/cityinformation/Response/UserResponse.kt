package com.example.cityinformation.Response

import com.example.cityinformation.Model.User

data class UserResponse (var error:Boolean,var message:String,var data:ArrayList<User>)