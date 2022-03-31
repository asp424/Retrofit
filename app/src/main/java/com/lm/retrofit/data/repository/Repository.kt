package com.lm.retrofit.data.repository

import com.google.gson.JsonObject
import com.lm.retrofit.data.api.APIResponse
import com.lm.retrofit.data.api.AnimeApi
import com.lm.retrofit.data.api.MemesApi
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface Repository {

    fun memes(): Flow<APIResponse<JsonObject>>

    fun anime(): Flow<APIResponse<JsonObject>>

    class Base(
        private val memesApi: MemesApi,
        private val animeApi: AnimeApi
    ) : Repository {

        override fun memes() =
            callbackFlow {
                memesApi.fetchMemes().request { trySendBlocking(it) }
                awaitClose()
            }.flowOn(IO)

        override fun anime(): Flow<APIResponse<JsonObject>> =
            callbackFlow {
                animeApi.fetchAnime().request { trySendBlocking(it) }
                awaitClose()
            }.flowOn(IO)

        private inline fun <T> Call<T>.request(crossinline onResult: (response: APIResponse<T>) -> Unit) {
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful) onResult(APIResponse.Success(response))
                    else onResult(APIResponse.Failure(response))
                }

                override fun onFailure(call: Call<T>, throwable: Throwable) {
                    onResult(APIResponse.Exception(throwable))
                }
            })
        }
    }
}