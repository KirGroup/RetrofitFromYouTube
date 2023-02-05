package com.example.youtubesearch.network

import com.example.youtubesearch.network.APIClient.SCH
import com.example.youtubesearch.network.APIClient.mx
import com.example.youtubesearch.network.APIClient.part
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface APIInterface {
    @GET("${SCH}+${part}")
    fun searchVideos(
        @Path("key") key: String?,
        @Path("q") q: String?
    ): Call<String>

    @GET("${SCH}+${part}+${mx}")
    fun searchVideo(@Query("key") key: String?, @Query("q") q: String): Call<ResponseBody>
}