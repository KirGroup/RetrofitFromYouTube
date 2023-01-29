package com.example.youtubesearch.models

import com.example.youtubesearch.models.MediumModel
import com.squareup.moshi.Json

class ThumbnailModel(
    @field:Json(name = "medium")
    var medium : MediumModel
)