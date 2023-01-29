package com.example.youtubesearch.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.youtubesearch.models.VideoModel

class DiffCallBack: DiffUtil.ItemCallback<VideoModel>(){
    override fun areItemsTheSame(oldItem: VideoModel, newItem: VideoModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: VideoModel, newItem: VideoModel): Boolean {
        return oldItem==newItem
    }
}