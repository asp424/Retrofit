package com.lm.retrofit.data.repository

import com.google.gson.JsonObject
import com.lm.retrofit.data.api.APIResponse
import com.lm.retrofit.data.api.AnimeApi
import com.lm.retrofit.data.api.Handler
import com.lm.retrofit.data.api.MemesApi
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun memes(): Flow<APIResponse<JsonObject>>

    fun anime(): Flow<APIResponse<JsonObject>>

    class Base(
        private val memesApi: MemesApi,
        private val animeApi: AnimeApi,
        private val handler: Handler
    ) : Repository {

        override fun memes() = handler.task(memesApi.fetchMemes())

        override fun anime() = handler.task(animeApi.fetchAnime())

    }
}