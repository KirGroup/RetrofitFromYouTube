package com.example.youtubesearch.adapters.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.youtubesearch.databinding.CustomItemLayoutBinding

class MyViewHolder(binding: CustomItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    var mImageViewThumbnail: ImageView = binding.imageviewCustomItemLayout
    var mTextViewTitle: TextView = binding.textviewCustomItemLayoutHeading
    var mTextViewDescription: TextView = binding.textviewCustomItemLayoutChannel
    var mTextViewTime: TextView = binding.textviewCustomItemLayoutViews
}