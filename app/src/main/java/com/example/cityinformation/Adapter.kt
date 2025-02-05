package com.example.cityinformation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cityinformation.Model.Place2
import com.example.cityinformation.Model.State
import com.example.cityinformation.User.CityView

class Adapter (var context: Context,var list:ArrayList<Place2>):RecyclerView.Adapter<Adapter.View>(){

    class View(item:android.view.View):RecyclerView.ViewHolder(item) {
        val Imagestarts=item.findViewById<ImageView>(R.id.Imagestarts)
        val states=item.findViewById<TextView>(R.id.states)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): View {
        return View(LayoutInflater.from(context).inflate(R.layout.card,parent,false))
    }

    override fun onBindViewHolder(holder: View, position: Int) {
        holder.Imagestarts.load(list[position].statepath)
        holder.states.text=list[position].state
        holder.itemView.setOnClickListener {
            val int=Intent(context,CityView::class.java)
            int.putExtra("state",list[position].state)
            context.startActivity(int)
        }
    }

    override fun getItemCount()=list.size
}