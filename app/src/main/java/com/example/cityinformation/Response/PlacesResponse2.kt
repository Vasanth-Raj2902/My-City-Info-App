package com.example.cityinformation.Response

import com.example.cityinformation.Model.Place2

data class PlacesResponse2 (var error:Boolean,var message:String,
var place:ArrayList<Place2>)