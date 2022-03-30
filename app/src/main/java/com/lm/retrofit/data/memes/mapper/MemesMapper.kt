package com.lm.retrofit.data.memes.mapper

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.lm.retrofit.core.Mapper

interface MemesMapper : Mapper.DataToUI<JsonObject, List<JsonElement>> {

    class Base : MemesMapper {
        override fun map(data: JsonObject?) =
            data?.get("data")?.asJsonObject?.get("memes")?.asJsonArray!!.toList()
    }
}