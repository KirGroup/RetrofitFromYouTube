package com.example.youtubesearch.domain.models

import com.squareup.moshi.Json

class MediumModel(
    @field:Json(name = "url")
    var url: String
)