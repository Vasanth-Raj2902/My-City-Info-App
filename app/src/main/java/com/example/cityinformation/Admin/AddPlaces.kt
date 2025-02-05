package com.example.cityinformation.Admin

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import coil.load
import com.example.cityinformation.Response.PlacesResponse2
import com.example.cityinformation.RetrofitIn
import com.example.cityinformation.databinding.ActivityAddPlacesBinding
import com.example.cityinformation.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

class AddPlaces : AppCompatActivity()/*, AdapterView.OnItemSelectedListener*/ {
    var encoded=""
    var urie=Uri.parse("")
    val data= arrayOf("Select a State","Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh",
        "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka",
        "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha",
        "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttarakhand", "Uttar Pradesh",
        "West Bengal", "Andaman and Nicobar Islands", "Chandigarh", "Dadra and Nagar Haveli", "Daman and Diu",
        "Delhi", "Lakshadweep", "Puducherry")
  val state=ArrayList<String>()
  val statepath=ArrayList<String>()
    private lateinit var bind:ActivityAddPlacesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityAddPlacesBinding.inflate(layoutInflater)
        setContentView(bind.root)

/*
        val p=ProgressDialog(this)
        p.setCancelable(false)
        p.setTitle("Loading.....")
        p.show()*/

        bind.images.setOnClickListener {
            val int=Intent(Intent.ACTION_GET_CONTENT)
            int.setType("image/*")
            startActivityForResult(Intent.createChooser(int,"Select a Image"),0)
        }

        val secspinner=bind.second
        secspinner.adapter=ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line, arrayOf(
            "Category","Tourist Places",
            "Hotels","Bus Stands","School","College","Hospitals","Government offices","Police station"))
        bind.next.setOnClickListener {
        val j=bind.spinner.selectedItem.toString()
            val k=secspinner.selectedItem.toString()
        if(j==data[0]) {
        it.toast("Please a State")
        }else if(encoded==""){
it.toast("Please Select Image")
        }else if(k=="Category"){
            it.toast("Please A Category")
        }else{
            val int=Intent(this,WriteCity::class.java)
            int.putExtra("state",j)
            int.putExtra("catogry",k)
            int.putExtra("uri",urie.toString())
            startActivity(int)
        }


        }



        bind.spinner.adapter=ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,data)
/*
        bind.spinner.onItemSelectedListener=this
*/




        /*CoroutineScope(IO).launch {
            RetrofitIn.instance.getstate("State").enqueue(object :Callback<PlacesResponse2>{
       override fun onResponse(call: Call<PlacesResponse2>, response: Response<PlacesResponse2>) {
                    p.dismiss()
                    response.body()!!.place.forEach {
                        state.add(it.state)
                        statepath.add(it.statepath)
                    }


                }
                override fun onFailure(call: Call<PlacesResponse2>, t: Throwable) {
                    Toast.makeText(this@AddPlaces, "${t.message}", Toast.LENGTH_SHORT).show()
                p.dismiss()
                }
            })
        }*/




    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    if(data!=null){
        val uri=Uri.parse(data.data.toString())
        urie=uri
        val bitmap=MediaStore.Images.Media.getBitmap(contentResolver,uri)
        val strem=ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,100,strem)
        val image=strem.toByteArray()
encoded=Base64.encodeToString(image,Base64.NO_WRAP)
        bind.images.load(bitmap)
    }

    }

/*    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (!state.contains(data[position])){
            bind.images.isVisible=true
            var num=0
            statepath.forEach {
                if(num==0){
                    Glide.with(this@AddPlaces).load(it).into(bind.images)
                }
                num++
            }
        }
    }*/

 /*   override fun onNothingSelected(parent: AdapterView<*>?) {

    }*/
}