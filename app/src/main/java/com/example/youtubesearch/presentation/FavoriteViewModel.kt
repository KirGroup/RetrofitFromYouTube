package com.example.youtubesearch.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.youtubesearch.data.VideoListRepositoryImpl
import com.example.youtubesearch.domain.models.VideoModel
import com.example.youtubesearch.domain.usecases.GetVideoListUseCase
import com.example.youtubesearch.domain.usecases.HideVideoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application)  {

    private val repository = VideoListRepositoryImpl(application)
    private val getVideoListUseCase = GetVideoListUseCase(repository)
    private val hideVideo = HideVideoUseCase(repository)

    private val _favoriteVideoModelList: MutableLiveData<List<VideoModel>> = MutableLiveData()
    val favoriteVideoModelList: LiveData<List<VideoModel>> = _favoriteVideoModelList

    fun getFavoriteVideoList() {
        viewModelScope.launch(Dispatchers.IO) {
            val favoritesResult = getVideoListUseCase.getVideoList()
            _favoriteVideoModelList.postValue(favoritesResult)
        }
    }

    fun hideVideo(videoModel: VideoModel, position: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            hideVideo.hideVideo(videoModel)
            val listAfterHide = _favoriteVideoModelList.value?.toMutableList()
            listAfterHide?.removeAt(position)
            _favoriteVideoModelList.postValue(listAfterHide)
        }
    }
}