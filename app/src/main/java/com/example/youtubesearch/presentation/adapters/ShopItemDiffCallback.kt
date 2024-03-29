package com.example.youtubesearch.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.youtubesearch.domain.models.VideoModel

class DiffCallBack : DiffUtil.ItemCallback<VideoModel>() {
    override fun areItemsTheSame(oldItem: VideoModel, newItem: VideoModel): Boolean {
        return oldItem.id.videoId == newItem.id.videoId
    }

    override fun areContentsTheSame(oldItem: VideoModel, newItem: VideoModel): Boolean {
        return oldItem==newItem
    }
}