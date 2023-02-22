package com.example.youtubesearch.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.youtubesearch.models.VideoModel

@Dao
interface DaoVideos {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(listVideos: List<VideoModel>)

    @Query("DELETE FROM VideoModelEntity")
    suspend fun clearVideos()

    @Query("SELECT*FROM VideoModelEntity")
    suspend fun getVideos()
}