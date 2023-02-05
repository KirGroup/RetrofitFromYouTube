package com.example.youtubesearch.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object APIClient {
    const val BASE_URL = "https://www.googleapis.com/youtube/v3/"
    const val API_KEY = "AIzaSyDmFZTIzBcTqZxijs9NDy0ZYPsbCbvSCLg"
    const val part = "&part=snippet"
    const val SCH = "search?"
    const val mx = "&maxResults=20"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        .addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .method(original.method, original.body)

            val request = requestBuilder.build()
            chain.proceed(request)
        }.build() //TODO change without 2 addInterceptor

    val instance: APIInterface by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
//            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(APIInterface::class.java)
    }
}