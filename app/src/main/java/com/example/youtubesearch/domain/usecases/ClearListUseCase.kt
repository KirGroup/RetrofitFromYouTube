package com.example.youtubesearch.domain.usecases

class ClearListUseCase(private val videoListRepository: VideoListRepository) {

    suspend fun clearVideos() {
        return videoListRepository.clearVideos()
    }
}