package com.example.youtubesearch.data.network

import com.example.youtubesearch.data.network.APIClient.SCH
import com.example.youtubesearch.data.network.APIClient.mx
import com.example.youtubesearch.data.network.APIClient.part
import com.example.youtubesearch.domain.models.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
//    @GET("${SCH}+${part}")
//    fun searchVideos(
//        @Path("key") key: String?,
//        @Path("q") q: String?
//    ): Call<ResponseModel>

    @GET("${SCH}+${part}+${mx}")
    fun searchVideo(
        @Query("key") key: String?,
        @Query("q") q: String
    ): Call<ResponseModel>
}