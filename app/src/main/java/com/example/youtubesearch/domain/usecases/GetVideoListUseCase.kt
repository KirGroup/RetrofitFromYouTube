package com.example.youtubesearch.domain.usecases

import com.example.youtubesearch.domain.models.VideoModel

class GetVideoListUseCase(private val videoListRepository: VideoListRepository) {

    fun getVideoList(): List<VideoModel> {
        return videoListRepository.getVideoList()
    }
}