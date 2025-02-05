package com.example.cityinformation.User

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cityinformation.R
import com.example.cityinformation.Response.CommonResponse
import com.example.cityinformation.Response.ContactsView
import com.example.cityinformation.RetrofitIn
import com.example.cityinformation.databinding.ActivityContactingBinding
import com.example.cityinformation.databinding.AddcontactBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactingActivity : AppCompatActivity() ,Ractions{
    private val bind by lazy {
        ActivityContactingBinding.inflate(layoutInflater)
    }
    private val dialogbind by lazy {
        AddcontactBinding.inflate(layoutInflater)
    }
    private val bottom by lazy {
        BottomSheetDialog(this)
            .apply {
                setTheme(R.style.transparent)
                setContentView(dialogbind.root)
            }

    }
    private val p by lazy {
        ProgressDialog(this).apply {
            setTitle("Fetching details.....................")
            setCancelable(false)
        }
    }

    private val viewmodel by lazy {
        ViewModelProvider(this)[ViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        val id=getSharedPreferences("user", MODE_PRIVATE).getString("id","")


        id?.let { viewmodel.getdata(it) }

        viewmodel.mutable.observe(this){it->
            if(it!=null){
                bind.cycle3.layoutManager=LinearLayoutManager(this)
                bind.cycle3.adapter= ContactAdapter(this,it,this)
            }
        }
        bind.addcall.setOnClickListener {
            bottom.show()
        }



        dialogbind.btnadd.setOnClickListener {
            val name=dialogbind.name .text.toString().trim()
            val mobile=dialogbind.mobile.text.toString().trim()
            if(name.isEmpty()){
                Toast.makeText(this, "Please enter your Name", Toast.LENGTH_SHORT).show()
            }else if(mobile.isEmpty()){
                Toast.makeText(this, "Please enter your Mobile number", Toast.LENGTH_SHORT).show()
            }else{
               p.show()
                RetrofitIn.instance.addcontact(userid = "$id", name = name, mobile_number = mobile).enqueue(object :Callback<CommonResponse>{
                    override fun onResponse(
                        call: Call<CommonResponse>,
                        response: Response<CommonResponse>
                    ) {
                        response.body()?.let {
                            Toast.makeText(this@ContactingActivity, it.message, Toast.LENGTH_SHORT).show()
                        if(it.message.contains("SuccessFully")){
                            bottom.dismiss()
                            id?.let { it1 -> viewmodel.getdata(it1) }
                            dialogbind.name.text?.clear()
                            dialogbind.mobile.text?.clear()
                        }
                        }
                        p.dismiss()

                    }

                    override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
                        p.dismiss()
                        Toast.makeText(this@ContactingActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }


        }

    }

    override fun select(contactView: ContactsView) {
        MaterialAlertDialogBuilder(this).apply {
            setTitle("Do you want delete contact of ${contactView.name}")
            setPositiveButton("Yes"){p,_->
                contactView.id?.let { deletefun(it) }
                p.dismiss()
            }
            setNegativeButton("No",){
                p,_->
                p.dismiss()
            }
            show()
        }
    }

    private fun deletefun(id: String) {
        p.show()
RetrofitIn.instance.deletefun(id = id).enqueue(object :Callback<CommonResponse>{
    override fun onResponse(call: Call<CommonResponse>, response: Response<CommonResponse>) {
        response.body()?.let {
            Toast.makeText(this@ContactingActivity, it.message, Toast.LENGTH_SHORT).show()
            if(it.message=="Success"){
                remove(id)
            }
            p.dismiss()
        }
    }

    override fun onFailure(call: Call<CommonResponse>, t: Throwable) {
        p.dismiss()
        Toast.makeText(this@ContactingActivity, "${t.message}", Toast.LENGTH_SHORT).show()
    }
})
    }
    fun remove(id:String){
        val array=ArrayList<ContactsView>()
    viewmodel.mutable.value?.forEach {
        if(id!=it.id) {
            array.add(it)
        }
    }
        viewmodel.mutable.value=array
    }

}