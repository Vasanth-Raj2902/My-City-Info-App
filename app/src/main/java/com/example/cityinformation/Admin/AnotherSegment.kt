package com.example.cityinformation.Admin

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.widget.Toast
import coil.load
import com.example.cityinformation.R
import com.example.cityinformation.Response.CommonResponse
import com.example.cityinformation.RetrofitIn
import com.example.cityinformation.databinding.ActivityAnotherSegmentBinding
import com.example.cityinformation.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import javax.microedition.khronos.opengles.GL

class AnotherSegment : AppCompatActivity() {
    var t = ""
    var uriw = Uri.parse("")
    private lateinit var bind: ActivityAnotherSegmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityAnotherSegmentBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.placeimage.setOnClickListener {
            val int = Intent(Intent.ACTION_GET_CONTENT)
            int.setType("image/*")
            startActivityForResult(Intent.createChooser(int, "Please select a image"), 0)
        }


        val state = intent.getStringExtra("state")
        val image = intent.getStringExtra("image")
        val catogry = intent.getStringExtra("catogry")
        val city = intent.getStringExtra("city")
        val cityimage = intent.getStringExtra("cityimage")

        bind.next3.setOnClickListener {
            val placname = bind.placename.text.toString()
            val placedis = bind.disc.text.toString()
            if (placname.isEmpty() || placedis.isEmpty()) {
                it.toast("Empty Field")
            } else if (t == "") {
                it.toast("Please Select a Image From a Your Gallery")
            } else {
                val firstout = ByteArrayOutputStream()
                val stateimage1 =
                    MediaStore.Images.Media.getBitmap(contentResolver, Uri.parse(image))
                stateimage1.compress(Bitmap.CompressFormat.PNG, 100, firstout)
                val compress1 = Base64.encodeToString(firstout.toByteArray(), Base64.NO_WRAP)

                val secondout = ByteArrayOutputStream()
                val stateimage2 =
                    MediaStore.Images.Media.getBitmap(contentResolver, Uri.parse(cityimage))
                stateimage2.compress(Bitmap.CompressFormat.PNG, 100, secondout)
                val compress2 = Base64.encodeToString(secondout.toByteArray(), Base64.NO_WRAP)

                val thirdout = ByteArrayOutputStream()
                val stateimage3 = MediaStore.Images.Media.getBitmap(contentResolver, uriw)
                stateimage3.compress(Bitmap.CompressFormat.PNG, 100, thirdout)
                val compress3 = Base64.encodeToString(thirdout.toByteArray(), Base64.NO_WRAP)
                val p = ProgressDialog(this)
                p.setCancelable(false)
                p.show()
                CoroutineScope(IO).launch {
                    RetrofitIn.instance.addplaces(
                        catogry!!, city!!, state!!,
                        compress2, compress1, placedis, placname, compress3
                    ).enqueue(object : Callback<CommonResponse> {
                        override fun onResponse(
                            call: Call<CommonResponse>,
                            response: Response<CommonResponse>
                        ) {
                            Toast.makeText(
                                this@AnotherSegment,
                                response.body()!!.message,
                                Toast.LENGTH_SHORT
                            ).show()
                            p.dismiss()
                        }

                        override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                            Toast.makeText(this@AnotherSegment, "${t.message}", Toast.LENGTH_SHORT)
                                .show()
                            p.dismiss()
                        }

                    })
                }
            }
        }


    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            uriw = Uri.parse(data.data.toString())
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uriw)
            bind.placeimage.load(bitmap)
            t = "HEllo"
        }
    }
}