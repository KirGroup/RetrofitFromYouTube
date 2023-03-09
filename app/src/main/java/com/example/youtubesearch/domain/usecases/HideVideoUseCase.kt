package com.example.youtubesearch.domain.usecases

import com.example.youtubesearch.domain.models.VideoModel

class HideVideoUseCase(private val videoListRepository: VideoListRepository) {

    suspend fun hideVideo(videoModel: VideoModel) {
        return videoListRepository.hideVideo(videoModel)
    }
}