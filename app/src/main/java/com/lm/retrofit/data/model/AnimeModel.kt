package com.lm.retrofit.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AnimeModel(
val mal_id:Int = 0,
val url: String = "",
val image_url: String = "",
val title: String = "",
val airing: Boolean = false,
val synopsis: String = "",
val type: String = "",
val episodes: Int = 0,
val score:Double = 0.0,
val start_date: String = "",
val members: Int = 0,
val rated: String = ""
)

class AnimeRequest {
	@SerializedName("results")
	@Expose
	var results: List<AnimeModel> = emptyList()
}

