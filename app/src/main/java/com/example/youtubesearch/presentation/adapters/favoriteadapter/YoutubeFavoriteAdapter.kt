package com.example.youtubesearch.presentation.adapters.favoriteadapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.youtubesearch.domain.models.VideoModel
import com.example.youtubesearch.presentation.adapters.DiffCallBack
import com.example.youtubesearch.presentation.adapters.holder.MyViewHolder
import com.youtubesearch.R
import com.youtubesearch.databinding.CustomItemLayoutBinding

class YoutubeFavoriteAdapter : ListAdapter<VideoModel, MyViewHolder>(
    DiffCallBack()
) {

    var onItemClickListener: ((VideoModel) -> Unit)? = null
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        return MyViewHolder(
            CustomItemLayoutBinding.inflate(LayoutInflater.from(context))
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.btnPlay.setOnClickListener {
            onItemClickListener?.invoke(getItem(position))
        }
        Log.d("onBindViewHolder", "onBindViewHolder: ${getItem(position).snippet.thumbnails.medium.url}")
        Glide.with(context).load(getItem(position).snippet.thumbnails.medium.url)
            .placeholder(R.drawable.splash_theme)
            .into(holder.imageViewThumbnail)
        holder.textViewTitle.text = getItem(position).snippet.title
        holder.textViewDescription.text = getItem(position).snippet.description
        holder.textViewTime.text = getItem(position).snippet.publishedAt
    }
}