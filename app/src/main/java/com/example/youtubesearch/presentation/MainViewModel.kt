package com.example.youtubesearch.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.youtubesearch.data.VideoListRepositoryImpl
import com.example.youtubesearch.domain.models.VideoModel
import com.example.youtubesearch.domain.usecases.ClearListUseCase
import com.example.youtubesearch.domain.usecases.GetSearchResultUseCase
import com.example.youtubesearch.domain.usecases.GetVideoListUseCase
import com.example.youtubesearch.domain.usecases.InsertVideoListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = VideoListRepositoryImpl

    private val insertVideo = InsertVideoListUseCase(repository)
    private val getSearchResult = GetSearchResultUseCase(repository)
    private val getVideoListUseCase = GetVideoListUseCase(repository)
    private val clearVideos = ClearListUseCase(repository)

    private val _videoModelList: MutableLiveData<List<VideoModel>> = MutableLiveData()
    val videoModelList: LiveData<List<VideoModel>> = _videoModelList

    suspend fun insertVideo(videoModel: VideoModel, context: Context) {
        insertVideo.insertVideos(videoModel, context)
    }

    fun getSearchResult(word: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val searchResult = getSearchResult.getSearchResult(word, context)
            _videoModelList.postValue(searchResult)

            if (searchResult.isNotEmpty())
                Log.d("fromApi", "viewModel receive ${searchResult[0].id.videoId}")
        }
    }

    suspend fun getVideoList() {
        getVideoListUseCase.getVideoList()
    }

    suspend fun clearVideos() {
        clearVideos.clearVideos()
    }
} 