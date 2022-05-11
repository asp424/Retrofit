package com.lm.retrofit.data.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MemesModel(
	val id: String = "",
	val name: String = "",
	val url: String = "",
	val width: Int = 0,
	val height: Int = 0,
	val box_count: Int = 0,
)

class MemesRequest {
	@SerializedName("data")
	@Expose
	var data: JsonObject? = null
}
