package com.example.cityinformation

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cityinformation.Model.State
import com.example.cityinformation.Response.Place3
import com.example.cityinformation.User.clcick

class Adapter2 (var context: Context,var list:ArrayList<Place3>,val kd: clcick):RecyclerView.Adapter<Adapter2.View2>(){

    class View2(item:View):RecyclerView.ViewHolder(item) {
        val Imagestarts=item.findViewById<ImageView>(R.id.Imagestarts)
        val states=item.findViewById<TextView>(R.id.states)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): View2{
        return View2(LayoutInflater.from(context).inflate(R.layout.card,parent,false))
    }

    override fun onBindViewHolder(holder: View2, position: Int) {
            holder.Imagestarts.load(list[position].citypath)
        holder.states.text=list[position].city
        holder.itemView.setOnClickListener {
            kd.click(list[position])
        }
    }

    override fun getItemCount()=list.size
}