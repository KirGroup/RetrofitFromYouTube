package com.example.youtubesearch.data.database

import androidx.room.*
import com.example.youtubesearch.domain.models.VideoModel

@Dao
interface DaoVideos {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(videoModel: VideoModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListVideos(videoModelList: List<VideoModel>)

    @Delete
    suspend fun hideVideo(videoModel: VideoModel)

    @Query("SELECT*FROM VideoModelEntity")
    suspend fun getVideos(): List<VideoModel>
}