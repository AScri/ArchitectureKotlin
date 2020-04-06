package com.ascri.test.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.ascri.test.R
import com.ascri.test.data.models.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_image_avatar.view.*


class ImageBaseAdapter2(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items: ArrayList<User> = ArrayList()

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, type: Int): RecyclerView.ViewHolder {
        return ViewHolderAvatar2(
            LayoutInflater.from(context).inflate(
                R.layout.item_image_avatar,
                parent,
                false
            ), items
        )
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holer: RecyclerView.ViewHolder, poisition: Int) {
        Glide.with(context)
            .load(items[poisition].image)
            .apply(RequestOptions.circleCropTransform())
            .into((holer as ViewHolderAvatar2).avarat)
        holer.name.text = items[poisition].name
        holer.innerAdapter.setData(items[poisition].items!!)
    }

    fun setData(users: List<User>) {
        items.addAll(users)
        notifyDataSetChanged()
    }
    fun clear(){
        items.clear()
        notifyDataSetChanged()
    }
}

class ViewHolderAvatar2(view: View, var data: ArrayList<User>) : RecyclerView.ViewHolder(view) {
    val avarat: ImageView = view.ivAvatar
    val name: TextView = view.tvUserName
    val innerImage: RecyclerView = view.rvInnerImage2
    private var linearLayoutManager: LinearLayoutManager =
        LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
    val innerAdapter = ImageAdapter(view.context)

    init {
        innerImage.setHasFixedSize(true)
        innerImage.layoutManager = linearLayoutManager
        innerImage.adapter = innerAdapter
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(innerImage)
    }
}