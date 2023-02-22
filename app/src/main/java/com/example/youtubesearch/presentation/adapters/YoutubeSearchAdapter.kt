package com.example.youtubesearch.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.youtubesearch.presentation.adapters.holder.MyViewHolder
import com.example.youtubesearch.domain.models.VideoModel
import com.youtubesearch.R
import com.youtubesearch.databinding.CustomItemLayoutBinding
import androidx.recyclerview.widget.ListAdapter as ListAdapterCards

class YoutubeSearchAdapter(
    var mContext: Context
) : ListAdapterCards<VideoModel, MyViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CustomItemLayoutBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Glide.with(mContext).load(getItem(position).snippet.thumbnails.medium.url)
            .placeholder(R.drawable.splash_theme)
            .into(holder.mImageViewThumbnail)
        holder.mTextViewTitle.text = getItem(position).snippet.title
        holder.mTextViewDescription.text = getItem(position).snippet.description
        holder.mTextViewTime.text = getItem(position).snippet.publishedAt
    }
}