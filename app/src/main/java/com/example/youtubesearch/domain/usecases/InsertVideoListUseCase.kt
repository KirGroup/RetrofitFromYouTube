package com.example.youtubesearch.domain.usecases

import android.content.Context
import com.example.youtubesearch.domain.models.VideoModel

class InsertVideoListUseCase(private val videoListRepository: VideoListRepository) {

    suspend fun insertVideos(listVideos: List<VideoModel>, context: Context) {
        return videoListRepository.insertVideo(listVideos, context)
    }
}