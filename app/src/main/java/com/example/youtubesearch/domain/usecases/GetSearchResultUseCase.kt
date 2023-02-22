package com.example.youtubesearch.domain.usecases

import android.content.Context
import com.example.youtubesearch.domain.models.VideoModel

class GetSearchResultUseCase(private val videoListRepository: VideoListRepository) {

    fun getSearchResult(word: String, context: Context): List<VideoModel> {
        return videoListRepository.getSearchResult(word, context)
    }
}