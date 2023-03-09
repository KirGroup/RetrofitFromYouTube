package com.example.youtubesearch.domain.usecases

import android.content.Context
import com.example.youtubesearch.data.database.VideosDataBase
import com.example.youtubesearch.domain.models.VideoModel

interface VideoListRepository {
    suspend fun insertVideo(videoModel: VideoModel, dbVideos: VideosDataBase)
    suspend fun getSearchResult(word: String): List<VideoModel>
    suspend fun getVideoList(): List<VideoModel>
    suspend fun hideVideo(videoModel: VideoModel)
}