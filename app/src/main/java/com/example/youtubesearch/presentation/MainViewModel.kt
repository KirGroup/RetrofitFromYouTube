package com.example.youtubesearch.presentation

import android.app.Application
import androidx.lifecycle.*
import com.example.youtubesearch.data.VideoListRepositoryImpl
import com.example.youtubesearch.domain.models.VideoModel
import com.example.youtubesearch.domain.usecases.HideVideoUseCase
import com.example.youtubesearch.domain.usecases.GetSearchResultUseCase
import com.example.youtubesearch.domain.usecases.GetVideoListUseCase
import com.example.youtubesearch.domain.usecases.InsertVideoListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.FieldPosition

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = VideoListRepositoryImpl(application)

    private val insertVideo = InsertVideoListUseCase(repository)
    private val getSearchResult = GetSearchResultUseCase(repository)
    private val hideVideo = HideVideoUseCase(repository)

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

    fun hideVideo(videoModel: VideoModel, position: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            hideVideo.hideVideo(videoModel)
            val listAfterHide = _videoModelList.value?.toMutableList()
            listAfterHide?.removeAt(position)
            _videoModelList.postValue(listAfterHide)
        }
    }
}