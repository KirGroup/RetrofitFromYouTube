package com.example.youtubesearch.domain.usecases

import com.example.youtubesearch.data.database.VideosDataBase
import com.example.youtubesearch.domain.models.VideoModel

class InsertVideoListUseCase(private val videoListRepository: VideoListRepository) {

    suspend fun insertVideos(videoModel: VideoModel, dbVideos: VideosDataBase) {
        return videoListRepository.insertVideo(videoModel, dbVideos)
    }
}