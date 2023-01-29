package com.example.youtubesearch.models

import com.squareup.moshi.Json

 data class VideoModel(
    @field:Json(name = "id")
    var id: VideoIDModel,
    @field:Json(name = "snippet")
    var snippet: SnippetModel
)