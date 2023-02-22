package com.example.youtubesearch.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.youtubesearch.models.DaoConverter
import com.example.youtubesearch.models.VideoModel

@Database(
    entities = [
        VideoModel::class
    ],
    version = 1
)
@TypeConverters(DaoConverter::class)

abstract class VideosDataBase : RoomDatabase() {

    abstract val daoVideos: DaoVideos

    companion object {
        const val NAME_DATABASE = "VideoModel.db"
    }
}