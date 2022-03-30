package com.lm.retrofit.data.memes.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface MemesApi {
    @GET("get_memes")
    fun fetchMemes(): Call<JsonObject>

    companion object {
        const val BASE_URL = "https://api.imgflip.com"
    }
}