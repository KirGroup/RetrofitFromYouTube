package com.example.youtubesearch.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.youtubesearch.data.VideoListRepositoryImpl
import com.example.youtubesearch.domain.models.VideoModel
import com.example.youtubesearch.domain.usecases.ClearListUseCase
import com.example.youtubesearch.domain.usecases.GetSearchResultUseCase
import com.example.youtubesearch.domain.usecases.GetVideoListUseCase
import com.example.youtubesearch.domain.usecases.InsertVideoListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application)  {

    private val repository = VideoListRepositoryImpl
    private val getVideoListUseCase = GetVideoListUseCase(repository)

    private val _favoriteVideoModelList: MutableLiveData<List<VideoModel>> = MutableLiveData()
    val favoriteVideoModelList: LiveData<List<VideoModel>> = _favoriteVideoModelList

    private var dbVideos = (getApplication() as App).database

    fun getFavoriteVideoList() {
        viewModelScope.launch(Dispatchers.IO) {
            val favoritesResult = getVideoListUseCase.getVideoList()
            _favoriteVideoModelList.postValue(favoritesResult)
        }
    }
}