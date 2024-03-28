package com.imprarce.android.testtaskdelivery.presentation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.imprarce.android.testtaskdelivery.R
import com.imprarce.android.testtaskdelivery.data.model.BannerItem
import com.squareup.picasso.Picasso

class BannerAdapter(private val bannerList: List<BannerItem>) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.banner_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_banner_recycler_view, parent, false)
        return BannerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val currentItem = bannerList[position]
        Picasso.get().load(currentItem.url).into(holder.imageView)
    }

    override fun getItemCount() = bannerList.size
}