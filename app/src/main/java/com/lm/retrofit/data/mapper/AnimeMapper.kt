package com.lm.retrofit.data.mapper

import android.util.Log
import com.google.gson.JsonObject
import com.lm.retrofit.core.Mapper
import com.lm.retrofit.data.model.AnimeModel

interface AnimeMapper : Mapper.DataToUI<JsonObject, MutableList<AnimeModel>> {

    class Base : AnimeMapper {
        override fun map(data: JsonObject?): MutableList<AnimeModel> {
            run {
                mutableListOf<AnimeModel>().also { list ->
                    data?.get("results")?.asJsonArray!!.forEach { e ->
                        e.asJsonObject.apply {
                            list.add(
                                AnimeModel(
                                    mal_id = get("mal_id").asInt,
                                    url = get("url").asString,
                                    image_url = get("image_url").asString,
                                    title = get("title").asString,
                                    airing = get("airing").asBoolean,
                                    synopsis = get("synopsis").asString,
                                    type = get("type").asString,
                                    episodes = get("episodes").asInt,
                                    score = get("score").asDouble,
                                   // start_date = get("start_date").asString,
                                    members = get("members").asInt,
                                    //rated = get("rated").asString
                                )
                            )
                        }
                    }
                    Log.d("My", list.size.toString())
                    return list
                }
            }
        }
    }
}