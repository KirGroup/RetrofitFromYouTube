package com.example.youtubesearch.models

import com.squareup.moshi.Json

class ErrorModel(
    @field:Json(name = "message")
    val message : String
)