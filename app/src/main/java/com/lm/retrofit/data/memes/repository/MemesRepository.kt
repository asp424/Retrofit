package com.lm.retrofit.data.memes.repository

import com.google.gson.JsonObject
import com.lm.retrofit.data.memes.api.APIResponse
import com.lm.retrofit.data.memes.api.MemesApi
import com.lm.retrofit.data.memes.api.request
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn

interface MemesRepository {

    suspend fun memes(): Flow<APIResponse<JsonObject>>

    class Base(private val memesApi: MemesApi) : MemesRepository {

        override suspend fun memes() =
            callbackFlow {
                memesApi.fetchMemes().request { trySendBlocking(it) }
                awaitClose()
            }.flowOn(IO)
    }
}