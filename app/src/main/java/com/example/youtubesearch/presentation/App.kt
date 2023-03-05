package com.example.youtubesearch.presentation

import android.app.Application
import androidx.room.Room
import com.example.youtubesearch.data.VideoListRepositoryImpl
import com.example.youtubesearch.data.database.VideosDataBase

class App : Application() {

    val database by lazy { VideosDataBase.getInstance(this) }

    override fun onCreate() {
        super.onCreate()
        // Initialize the Room database here
        database
//        VideoListRepositoryImpl.dbVideos = Room.databaseBuilder(
//            context.applicationContext,
//            VideosDataBase::class.java,
//            VideosDataBase.NAME_DATABASE
//        ).fallbackToDestructiveMigration().allowMainThreadQueries()
//            .build()
    }
}
