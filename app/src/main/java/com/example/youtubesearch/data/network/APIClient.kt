package com.example.youtubesearch.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object APIClient {
    private const val BASE_URL = "https://www.googleapis.com/youtube/v3/"
    const val API_KEY = "AIzaSyBrU621RIfkCzOA213E558O9WW33WMjh7Y"

    //    const val API_KEY = "AIzaSyB4SC8xrZutFZz1CqqSBop8hCYuwLDONiw"
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
        }
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(level = HttpLoggingInterceptor.Level.BODY)
        ).build()

    val instance: APIInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(APIInterface::class.java)
    }

}