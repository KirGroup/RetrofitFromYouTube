package com.example.youtubesearch.adapters.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.youtubesearch.R

class MyViewHolder(mItemView: View) : RecyclerView.ViewHolder(mItemView) {
    var mImageViewThumbnail: ImageView =
        mItemView.findViewById(R.id.imageviewCustomItemLayout)

    var mTextViewTitle: TextView =
        mItemView.findViewById(R.id.textviewCustomItemLayoutHeading)

    var mTextViewDescription: TextView =
        mItemView.findViewById(R.id.textviewCustomItemLayoutChannel)

    var mTextViewTime: TextView =
        mItemView.findViewById(R.id.textviewCustomItemLayoutViews)
}