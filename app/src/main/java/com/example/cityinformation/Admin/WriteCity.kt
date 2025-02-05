package com.example.cityinformation.Admin

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.cityinformation.R
import com.example.cityinformation.Response.PlacesResponse2
import com.example.cityinformation.RetrofitIn
import com.example.cityinformation.databinding.ActivityWriteCityBinding
import com.example.cityinformation.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WriteCity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var bind:ActivityWriteCityBinding
    val city=ArrayList<String>()
    val citypath=ArrayList<String>()
    var encoded=""
    var uri=Uri.parse("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind=ActivityWriteCityBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val state=intent.getStringExtra("state")
        val image=intent.getStringExtra("uri")
        val catogry=intent.getStringExtra("catogry")

        bind.cityimage.setOnClickListener {
            val int=Intent(Intent.ACTION_GET_CONTENT)
            int.setType("image/*")
            startActivityForResult(Intent.createChooser(int,""),0)
        }

        bind.cityspinner.onItemSelectedListener=this

        bind.next2.setOnClickListener {
            val tetxt=bind.city.text.toString()
            if(uri.toString()==""){
                it.toast("Please Select a image")
            }else{
                if (!tetxt.isEmpty()) {
                    val int = Intent(this, AnotherSegment::class.java)
                    int.putExtra("state", state)
                    int.putExtra("image", image)
                    int.putExtra("catogry", catogry)
                    int.putExtra("city",tetxt)
                    int.putExtra("cityimage",uri.toString())
                    /*               int.putExtra("")*/
                    startActivity(int)
                }
            }}




    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    if(data!=null){
         uri= Uri.parse(data.data.toString())
        val bitmap=MediaStore.Images.Media.getBitmap(contentResolver,uri)
        bind.cityimage.setImageBitmap(bitmap)
    }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        bind.city.setText(city[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?){

    }
}