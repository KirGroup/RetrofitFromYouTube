package com.example.youtubesearch.domain.models

import com.squareup.moshi.Json

class ErrorModel(
    @field:Json(name = "message")
    val message: String
)