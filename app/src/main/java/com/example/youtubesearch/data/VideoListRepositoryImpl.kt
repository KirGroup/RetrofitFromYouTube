package com.example.youtubesearch.data

import android.content.Context
import android.widget.Toast
import androidx.room.Room
import com.example.youtubesearch.data.database.VideosDataBase
import com.example.youtubesearch.data.network.APIClient
import com.example.youtubesearch.domain.models.ErrorModel
import com.example.youtubesearch.domain.models.ResponseModel
import com.example.youtubesearch.domain.models.VideoModel
import com.example.youtubesearch.domain.usecases.VideoListRepository
import com.youtubesearch.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object VideoListRepositoryImpl: VideoListRepository {

    private lateinit var dbVideos: VideosDataBase
    private var mVideoModelList: MutableList<VideoModel> = mutableListOf()


    override suspend fun insertVideo(videoModel: VideoModel, context: Context) {
        dbVideos = Room.databaseBuilder(
            context.applicationContext,
            VideosDataBase::class.java,
            VideosDataBase.NAME_DATABASE
        ).fallbackToDestructiveMigration().allowMainThreadQueries()
            .build()
        dbVideos.daoVideos.insertVideos(videoModel)
    }

    override fun getSearchResult(word: String, context: Context): List<VideoModel> {
        if (!word.equals("")) {

                APIClient.instance.searchVideo(
                    APIClient.API_KEY,
                    word
                ).enqueue(object : Callback<ResponseModel> {
                    override fun onResponse(
                        call: Call<ResponseModel>,
                        response: Response<ResponseModel>
                    ) {

                        if (response.isSuccessful) {
                            val mResponseModel: ResponseModel? = response.body()
                            if (mResponseModel != null) {
                                val mErrorModel: ErrorModel = mResponseModel.error
                                if (mErrorModel != null) {
//                                    Toast.makeText(it, mErrorModel.message, Toast.LENGTH_SHORT)
//                                        .show()
                                } else {
                                    mVideoModelList.addAll(mResponseModel.items)
                                }

                            } else {
//                                Toast.makeText(
//                                    it,
//                                    context.getString(R.string.text_string_no_data_found),
//                                    Toast.LENGTH_SHORT
//                                ).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
//                        Toast.makeText(it, t.message, Toast.LENGTH_SHORT)
//                            .show()
                    }
                })
            }

        return mVideoModelList
    }

    override suspend fun getVideoList(): List<VideoModel> {
        return dbVideos.daoVideos.getVideos()
    }

    override suspend fun clearVideos() {
        dbVideos.daoVideos.clearVideos()
    }
}