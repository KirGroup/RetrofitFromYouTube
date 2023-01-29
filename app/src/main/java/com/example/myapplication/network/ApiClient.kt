package com.example.myapplication.network

import com.android.youtubesearch.network.APIInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
    const val BASE_URL = "https://www.googleapis.com/youtube/v3/"
    //const val API_KEY = "AIzaSyA8oLcOCrDvURo02XHxt51JtTMFVo9npjM"
    const val API_KEY = "AIzaSyDmFZTIzBcTqZxijs9NDy0ZYPsbCbvSCLg"
    const val part = "&part=snippet"
    const val SCH = "search?"
    const val mx = "&maxResults=20"


    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .method(original.method, original.body)

            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    val instance: APIInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(APIInterface::class.java)
    }

}