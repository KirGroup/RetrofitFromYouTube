package com.example.youtubesearch.presentation.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.youtubesearch.presentation.adapters.holder.MyViewHolder
import com.example.youtubesearch.domain.models.VideoModel
import com.youtubesearch.R
import com.youtubesearch.databinding.CustomItemLayoutBinding
import androidx.recyclerview.widget.ListAdapter as ListAdapterCards

class YoutubeSearchAdapter(
    var mContext: Context
) : ListAdapterCards<VideoModel, MyViewHolder>(DiffCallBack()) {

    var onItemClickListener: ((VideoModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CustomItemLayoutBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.btnPlay.setOnClickListener {
//                MainViewModel().insertVideo(getItem(position), mContext)
            onItemClickListener?.invoke(getItem(position))
        }

        Glide.with(mContext).load(getItem(position).snippet.thumbnails.medium.url)
            .placeholder(R.drawable.splash_theme)
            .into(holder.imageViewThumbnail)
        holder.textViewTitle.text = getItem(position).snippet.title
        holder.textViewDescription.text = getItem(position).snippet.description
        holder.textViewTime.text = getItem(position).snippet.publishedAt
    }
}