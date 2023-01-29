package com.example.youtubesearch.models

import com.example.youtubesearch.models.SnippetModel
import com.example.youtubesearch.models.VideoIDModel
import com.squareup.moshi.Json

class VideoModel (
    @field:Json(name = "id")
    var id : VideoIDModel,
    @field:Json(name = "snippet")
    var snippet : SnippetModel
)