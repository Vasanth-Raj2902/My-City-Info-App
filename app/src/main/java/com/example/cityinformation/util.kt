package com.example.cityinformation

import android.view.View
import android.widget.Toast

fun View.toast (message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}