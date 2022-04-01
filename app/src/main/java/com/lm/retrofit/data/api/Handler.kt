package com.lm.retrofit.data.api

import com.google.gson.JsonObject
import com.lm.retrofit.data.api.Callback.request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call

interface Handler {

    fun task(call: Call<JsonObject>): Flow<APIResponse<JsonObject>>

    class Base(private val callback: Callback): Handler{

        override fun task(call: Call<JsonObject>) =
            callbackFlow {
                callback.apply { call.request { trySendBlocking(it) } }
                awaitClose()
            }.flowOn(Dispatchers.IO)
    }
}