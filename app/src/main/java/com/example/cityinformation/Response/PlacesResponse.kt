package com.example.cityinformation.Response

import com.example.cityinformation.Model.Places

data class PlacesResponse (var error:Boolean,
                           var message:String,
var place:ArrayList<Places>)