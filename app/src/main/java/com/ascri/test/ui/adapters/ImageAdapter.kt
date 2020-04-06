package com.ascri.test.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ascri.test.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_image.view.*

class ImageAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var urls: List<String> = listOf()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_image, p0, false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(context)
            .load(urls[position])
            .into((holder as ViewHolder).image)
    }

    fun setData(users: List<String>) {
        urls = users
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return urls.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.ivImage
    }
}