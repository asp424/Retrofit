package com.lm.retrofit.data.memes.mapper

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.lm.retrofit.core.Mapper

interface MemesMapper<out T>: Mapper.DataToUI<JsonObject, List<JsonElement>> {

    fun <U> map(mapper: Mapper.DataToUI<in T, U>): U

}