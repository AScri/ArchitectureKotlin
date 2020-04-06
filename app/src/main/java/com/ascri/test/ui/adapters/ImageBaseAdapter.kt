package com.ascri.test.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ascri.test.R
import com.ascri.test.data.models.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_image_avatar.view.*

class ImageBaseAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items: ArrayList<User> = ArrayList()

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        return ViewHolderAvatar(
            LayoutInflater.from(context).inflate(
                R.layout.item_image_avatar,
                parent,
                false
            ), items
        )
    }

    override fun onBindViewHolder(holer: RecyclerView.ViewHolder, poisition: Int) {
        Glide.with(context)
            .load(items[poisition].image)
            .apply(RequestOptions.circleCropTransform())
            .into((holer as ViewHolderAvatar).avarat)
        holer.name.text = items[poisition].name
        holer.innerAdapter.setData(items[poisition].items!!)
    }

    fun setData(users: List<User>) {
        items.addAll(users)
        notifyDataSetChanged()
    }
}

class ViewHolderAvatar(view: View, var data: ArrayList<User>) : RecyclerView.ViewHolder(view) {
    val avarat: ImageView = view.ivAvatar
    val name: TextView = view.tvUserName
    val innerImage: RecyclerView = view.rvInnerImage
    private var linearLayoutManager: GridLayoutManager = GridLayoutManager(view.context, 2)
    val innerAdapter = ImageAdapter(view.context)

    init {
        innerImage.setHasFixedSize(true)
        innerImage.layoutManager = linearLayoutManager
        val value = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (position == 0 && data[adapterPosition].items!!.size % 2 != 0) return 2
                return 1

            }
        }
        linearLayoutManager.spanSizeLookup = value
        innerImage.adapter = innerAdapter
    }
}