package com.lm.retrofit.data.memes.repository

import android.util.Log
import com.google.gson.JsonObject
import com.lm.retrofit.core.APIResponse
import com.lm.retrofit.data.memes.api.MemesApi
import com.lm.retrofit.data.memes.retrofit.request
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