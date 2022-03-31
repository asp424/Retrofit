package com.lm.retrofit.data.mapper

import android.util.Log
import com.google.gson.JsonObject
import com.lm.retrofit.core.Mapper
import com.lm.retrofit.data.model.MemModel

interface MemesMapper: Mapper.DataToUI<JsonObject, MutableList<MemModel>> {

    class Base : MemesMapper {
        override fun map(data: JsonObject?): MutableList<MemModel> {
            run {
                mutableListOf<MemModel>().apply {
                    data?.get("data")?.asJsonObject?.get("memes")?.asJsonArray!!
                        .forEach {
                        it.asJsonObject.apply {
                            add(
                                MemModel(
                                    id = get("id").asString,
                                    name = get("name").asString,
                                    url = get("url").asString,
                                    width = get("width").asInt,
                                    height = get("height").asInt,
                                    box_count = get("box_count").asInt,
                                )
                            )
                        }
                    }
                    Log.d("My", this.size.toString())
                    return this
                }
            }
        }
    }
}