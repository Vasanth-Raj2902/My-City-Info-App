package com.example.cityinformation.User

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Message
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.cityinformation.Response.ContactsView
import com.example.cityinformation.databinding.ContactcardBinding
import com.google.gson.annotations.SerializedName

data class ContactAdapter(
    val context: Context,
    val array: ArrayList<ContactsView>, val ractions: Ractions
) : RecyclerView.Adapter<ContactAdapter.Viewedpart>() {
    class Viewedpart(val item: ContactcardBinding) : RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Viewedpart(
        ContactcardBinding.inflate(LayoutInflater.from(context), parent, false)
    )

    override fun onBindViewHolder(holder: Viewedpart, position: Int) {
        val k = array[position]
        with(holder.item) {
            details.text=spanned("<big>${k.name}</big><br>" +
                                        "${k.mobileNumber}")




            callview.setOnClickListener {
                context.startActivity(
                    Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse("tel:${k.mobileNumber}")
                    )
                )
            }
            delete.setOnClickListener {
                ractions.select(k)
            }
        }
    }

    private fun functions()=if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        context.checkSelfPermission(android.Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_DENIED
    } else {
        ActivityCompat.checkSelfPermission(context,android.Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_DENIED
    }


    private fun spanned(string:String)= HtmlCompat.fromHtml(string,HtmlCompat.FROM_HTML_OPTION_USE_CSS_COLORS)


    override fun getItemCount() = array.size
}
