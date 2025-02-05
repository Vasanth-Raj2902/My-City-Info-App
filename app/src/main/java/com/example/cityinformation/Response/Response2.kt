package com.example.cityinformation.Response

import android.os.Message
import com.example.cityinformation.Model.State

data class Response2 (var error:Boolean,var message: String,var place:ArrayList<State>)