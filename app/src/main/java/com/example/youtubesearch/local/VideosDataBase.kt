package com.example.youtubesearch.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.youtubesearch.models.VideoModel

@Database(
    entities = [
        VideoModel::class
    ],
    version = 1
)
abstract class VideosDataBase : RoomDatabase() {

    abstract val daoVideos: DaoVideos

    companion object {
        const val NAME_DATABASE = "VideoModel.db"
    }
}