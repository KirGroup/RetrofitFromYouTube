package com.example.myapplication.models

import com.squareup.moshi.Json
import retrofit2.http.Field

class ErrorModel(
    @field:Json(name = "message")
    val message : String
)