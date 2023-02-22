package com.example.youtubesearch.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.squareup.moshi.Json

@Entity(tableName = "VideoModelEntity")
 data class VideoModel(
    @PrimaryKey
//    @TypeConverters(DaoConverter::class)
    @field:Json(name = "id")
    var id: VideoIDModel,
//    @TypeConverters(DaoConverter::class)
    @field:Json(name = "snippet")
    var snippet: SnippetModel
)

class DaoConverter {

      @TypeConverter
      fun idToString(videoIDModel: VideoIDModel): String =
         videoIDModel.videoId

      @TypeConverter
      fun snippetToString(snippetModel: SnippetModel): String {
         return snippetModel.title + ":" + snippetModel.thumbnails.medium.url
      }

      @TypeConverter
      fun stringToId(id: String): VideoIDModel =
         VideoIDModel("", id)

      @TypeConverter
      fun stringToSnippet(id: String): SnippetModel =
         SnippetModel("", id.split(":")[0], "", ThumbnailModel(MediumModel(id.split(":")[1])))

}