package com.example.youtubesearch.domain.usecases

import com.example.youtubesearch.domain.models.VideoModel

class InsertVideoListUseCase(private val videoListRepository: VideoListRepository) {

    suspend fun insertVideos(videoModel: VideoModel) {
        return videoListRepository.insertVideo(videoModel)
    }
}