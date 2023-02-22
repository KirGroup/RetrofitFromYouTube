package com.example.youtubesearch.presentation.adapters.holder

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.youtubesearch.databinding.CustomItemLayoutBinding

class MyViewHolder(binding: CustomItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    var btnPlay: ImageView = binding.btnPlay
    var imageViewThumbnail: ImageView = binding.imageviewCustomItemLayout
    var textViewTitle: TextView = binding.textviewCustomItemLayoutHeading
    var textViewDescription: TextView = binding.textviewCustomItemLayoutChannel
    var textViewTime: TextView = binding.textviewCustomItemLayoutViews
}