package com.example.youtubesearch.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.youtubesearch.domain.models.DaoConverter
import com.example.youtubesearch.domain.models.VideoModel

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
        @Volatile
        private var instance: VideosDataBase? = null

        private const val NAME_DATABASE = "VideoModel.db"

        fun getInstance(context: Context): VideosDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): VideosDataBase {
            return Room.databaseBuilder(context, VideosDataBase::class.java, NAME_DATABASE)
                .build()
        }
    }
}