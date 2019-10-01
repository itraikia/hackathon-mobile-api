package com.ibram.hackathon

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.place_item.view.*


class PlacesAdapter(val places: ArrayList<MyPlace>, val context : Activity) : RecyclerView.Adapter<PlaceViewHolder>() {



    override fun onBindViewHolder(p0: PlaceViewHolder, p1: Int) {
        val pos = places[p1]
        p0.title.text = pos.address
        p0.postTitle.text = pos.latLng.toString()

        p0.itemView.setOnClickListener {

        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlaceViewHolder {
        return PlaceViewHolder(LayoutInflater.from(context).inflate(R.layout.place_item,p0, false))
    }

    override fun getItemCount(): Int {
        return places.size
    }



}


class PlaceViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each driver to
    val image = view.image
    val title = view.title
    val postTitle = view.post_title
}
