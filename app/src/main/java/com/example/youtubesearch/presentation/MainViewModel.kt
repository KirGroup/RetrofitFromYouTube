package com.example.youtubesearch.presentation

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.youtubesearch.data.VideoListRepositoryImpl
import com.example.youtubesearch.data.database.VideosDataBase
import com.example.youtubesearch.domain.models.VideoModel
import com.example.youtubesearch.domain.usecases.ClearListUseCase
import com.example.youtubesearch.domain.usecases.GetSearchResultUseCase
import com.example.youtubesearch.domain.usecases.GetVideoListUseCase
import com.example.youtubesearch.domain.usecases.InsertVideoListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application)  {

    private val repository = VideoListRepositoryImpl(application)

    private val insertVideo = InsertVideoListUseCase(repository)
    private val getSearchResult = GetSearchResultUseCase(repository)
    private val getVideoListUseCase = GetVideoListUseCase(repository)
    private val clearVideos = ClearListUseCase(repository)

    private val _videoModelList: MutableLiveData<List<VideoModel>> = MutableLiveData()
    val videoModelList: LiveData<List<VideoModel>> = _videoModelList

    private var dbVideos = (getApplication() as App).database

    fun insertVideo(videoModel: VideoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            insertVideo.insertVideos(videoModel, dbVideos)
        }
    }

    fun getSearchResult(word: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val searchResult = getSearchResult.getSearchResult(word)
            _videoModelList.postValue(searchResult)
        }
    }

    suspend fun getVideoList() {
        getVideoListUseCase.getVideoList()
    }

    suspend fun clearVideos() {
        clearVideos.clearVideos()
    }
}