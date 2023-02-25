package com.example.youtubesearch.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.youtubesearch.domain.models.VideoModel

@Dao
interface DaoVideos {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(videoModel: VideoModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListVideos(videoModelList: List<VideoModel>)

    @Query("DELETE FROM VideoModelEntity")
    suspend fun clearVideos()

    @Query("SELECT*FROM VideoModelEntity")
    suspend fun getVideos():LiveData<List<VideoModel>>
}