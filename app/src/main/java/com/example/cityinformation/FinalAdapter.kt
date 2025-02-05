package com.example.cityinformation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load

import com.example.cityinformation.Model.State
import com.example.cityinformation.User.clcick

class FinalAdapter(var context: Context, var list: ArrayList<State>) :
    RecyclerView.Adapter<FinalAdapter.View3>() {

    class View3(item: View) : RecyclerView.ViewHolder(item) {
        val Imagestarts = item.findViewById<ImageView>(R.id.Imagestarts)
        val states = item.findViewById<TextView>(R.id.states)
        val card = item.findViewById<CardView>(R.id.cardview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): View3 {
        return View3(LayoutInflater.from(context).inflate(R.layout.card, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: View3, position: Int) {
        val dd = list[position]
        holder.card.isVisible = true
        holder.Imagestarts.load(dd.placepath)
        holder.states.text = list[position].placename
        var num = 1
        holder.itemView.setOnClickListener {
            num++
            if (num % 2 == 0) {
                holder.states.text = HtmlCompat.fromHtml(
                    "<b>Place :</b>" + list[position].placediscription,
                    FROM_HTML_MODE_LEGACY
                )
            } else {
                holder.states.text = HtmlCompat.fromHtml(
                    "<b>About :</b>" + list[position].placename,
                    FROM_HTML_MODE_LEGACY
                )
            }


        }


        holder.card.setOnClickListener {
            try {
                "${dd.state},${dd.city},${dd.placename}".let {
                    Geocoder(context).getFromLocationName(it, 1)[0].apply {
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("google.navigation:q=$latitude,$longitude&mode=b")
                        )
                            .also {
                                it.`package` = "com.google.android.apps.maps"
                                context.startActivity(it)
                            }
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Sorry Not Getting Location", Toast.LENGTH_SHORT).show()
            }


        }
    }

    override fun getItemCount() = list.size
}