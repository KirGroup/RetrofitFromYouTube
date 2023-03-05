package com.example.youtubesearch.data

import androidx.room.Room
import com.example.youtubesearch.data.database.VideosDataBase
import com.example.youtubesearch.data.network.APIClient
import com.example.youtubesearch.domain.models.ResponseModel
import com.example.youtubesearch.domain.models.VideoModel
import com.example.youtubesearch.domain.usecases.VideoListRepository

object VideoListRepositoryImpl : VideoListRepository {

    private lateinit var dbVideos: VideosDataBase

    override suspend fun insertVideo(videoModel: VideoModel) {
        dbVideos.daoVideos.insertVideos(videoModel)
    }

    override suspend fun getSearchResult(word: String): List<VideoModel> {

        if (word.isNotEmpty()) {
            val response = APIClient.instance.searchVideo(
                APIClient.API_KEY,
                word
            ).execute()

            if (response.isSuccessful) {
                val mResponseModel: ResponseModel? = response.body()
                if (mResponseModel != null) return mResponseModel.items
                else throw IllegalStateException()
            }
            throw IllegalStateException()
        }
        return listOf()
    }

    override suspend fun getVideoList(): List<VideoModel> {
        return dbVideos.daoVideos.getVideos()
    }

    override suspend fun clearVideos() {
        dbVideos.daoVideos.clearVideos()
    }
}