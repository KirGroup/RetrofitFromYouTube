package com.example.youtubesearch.presentation.adapters.searchadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.youtubesearch.domain.models.VideoModel
import com.example.youtubesearch.presentation.MainViewModel
import com.example.youtubesearch.presentation.adapters.CommonViewHolder
import com.example.youtubesearch.presentation.adapters.DiffCallBack
import com.youtubesearch.R
import com.youtubesearch.databinding.CommonItemLayoutBinding
import androidx.recyclerview.widget.ListAdapter as ListAdapterCards

class YoutubeSearchAdapter(val viewModel: MainViewModel) :
    ListAdapterCards<VideoModel, CommonViewHolder>(
        DiffCallBack()
    ) {

    var onItemClickListener: ((VideoModel) -> Unit)? = null
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        context = parent.context
        return CommonViewHolder(
            CommonItemLayoutBinding.inflate(LayoutInflater.from(context))
        )
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        holder.btnPlay.setOnClickListener {
            viewModel.insertVideo(getItem(position))
            onItemClickListener?.invoke(getItem(position))
        }

        Glide.with(context).load(getItem(position).snippet.thumbnails.medium.url)
            .placeholder(R.drawable.splash_theme)
            .into(holder.imagePreview)

        holder.time.text = getItem(position).snippet.publishedAt
        holder.title.text = getItem(position).snippet.title
        holder.description.text = getItem(position).snippet.description
    }
}