package com.example.youtubesearch.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.squareup.moshi.Json

const val DELIMITER = "space"

@Entity(tableName = "VideoModelEntity")
data class VideoModel(
    @PrimaryKey
    @field:Json(name = "id")
    var id: VideoIDModel,
    @field:Json(name = "snippet")
    var snippet: SnippetModel
)

class DaoConverter {

    @TypeConverter
    fun idToString(videoIdModel: VideoIDModel): String =
        videoIdModel.videoId

    @TypeConverter
    fun snippetToString(snippetModel: SnippetModel): String {
        return snippetModel.publishedAt + DELIMITER + snippetModel.title + DELIMITER +
               snippetModel.description + DELIMITER + snippetModel.thumbnails.medium.url
    }

    @TypeConverter
    fun stringToId(responseId: String): VideoIDModel =
        VideoIDModel("", responseId)

    @TypeConverter
    fun stringToSnippet(responseSnippet: String): SnippetModel {
        val values = responseSnippet.split(DELIMITER)
        return SnippetModel(
            values[0],
            values[1],
            values[2],
            ThumbnailModel(MediumModel(values[3]))
        )
    }
}