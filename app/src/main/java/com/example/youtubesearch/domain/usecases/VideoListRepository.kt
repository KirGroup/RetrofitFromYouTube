package com.example.youtubesearch.domain.usecases

import android.content.Context
import com.example.youtubesearch.domain.models.VideoModel

interface  VideoListRepository {
    suspend fun insertVideo(listVideos: List<VideoModel>, context: Context)
    fun getSearchResult(word: String, context: Context): List<VideoModel>
    fun getVideoList(): List<VideoModel>
    suspend fun clearVideos()
}