package com.example.youtubesearch.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youtubesearch.data.VideoListRepositoryImpl
import com.example.youtubesearch.domain.models.VideoModel
import com.example.youtubesearch.domain.usecases.ClearListUseCase
import com.example.youtubesearch.domain.usecases.GetSearchResultUseCase
import com.example.youtubesearch.domain.usecases.GetVideoListUseCase
import com.example.youtubesearch.domain.usecases.InsertVideoListUseCase

class MainViewModel: ViewModel() {

    private val repository = VideoListRepositoryImpl

    private val insertVideo = InsertVideoListUseCase(repository)
    private val getSearchResult = GetSearchResultUseCase(repository)
    private val getVideoListUseCase = GetVideoListUseCase(repository)
    private val clearVideos = ClearListUseCase(repository)

    private val _videoModelList: MutableLiveData<List<VideoModel>> = MutableLiveData()
    val videoModelList: LiveData<List<VideoModel>> = _videoModelList

    suspend fun insertVideo(videoModel: VideoModel, context: Context){
        insertVideo.insertVideos(videoModel, context)
    }

    fun getSearchResult(word: String, context: Context){
        _videoModelList.postValue(getSearchResult.getSearchResult(word, context))
    }

    suspend fun getVideoList(){
        getVideoListUseCase.getVideoList()
    }

    suspend fun clearVideos(){
        clearVideos.clearVideos()
    }
}