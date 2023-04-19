package com.example.rijksmuseumapp.ui.main

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rijksmuseumapp.R
import com.example.rijksmuseumapp.entity.ArtObject
import com.squareup.picasso.Picasso

class ArtObjectAdapter(private val listener: OnArtObjectClickListener) : RecyclerView.Adapter<ArtObjectAdapter.ArtObjectViewHolder>() {

    interface OnArtObjectClickListener {
        fun onArtObjectClicked(artObjectId: String)
    }

    private var artObjects = mutableListOf<ArtObject>()

    fun addData(data: List<ArtObject>) {
        artObjects.addAll(data)
        Handler(Looper.getMainLooper()).post {
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtObjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.art_object_item, parent, false)
        return ArtObjectViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ArtObjectViewHolder, position: Int) {
        val artObject = artObjects[position]
        holder.bind(artObject)
    }

    override fun getItemCount(): Int {
        return artObjects.size
    }

    inner class ArtObjectViewHolder(itemView: View, private val listener: OnArtObjectClickListener) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.image)
        private val titleTextView: TextView = itemView.findViewById(R.id.title)

        fun bind(artObject: ArtObject) {
            Picasso.get().load(artObject.webImage?.url).into(imageView)
            titleTextView.text = artObject.title

            itemView.setOnClickListener {
                listener.onArtObjectClicked(artObject.objectNumber)
            }
        }
    }
}