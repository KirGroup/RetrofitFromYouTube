package com.example.youtubesearch.domain.usecases

import android.content.Context
import com.example.youtubesearch.domain.models.VideoModel

interface VideoListRepository {
    suspend fun insertVideo(videoModel: VideoModel)
    suspend fun getSearchResult(word: String): List<VideoModel>
    suspend fun getVideoList(): List<VideoModel>
    suspend fun clearVideos()
}