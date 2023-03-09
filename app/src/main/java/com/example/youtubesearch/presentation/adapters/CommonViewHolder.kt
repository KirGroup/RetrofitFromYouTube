package com.example.youtubesearch.presentation.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.youtubesearch.databinding.CommonItemLayoutBinding

class CommonViewHolder(binding: CommonItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    var btnPlay: ImageView = binding.btnPlay
    var imagePreview: ImageView = binding.imagePreview
    var title: TextView = binding.tvTitle
    var description: TextView = binding.tvDescription
    var time: TextView = binding.tvTime
}