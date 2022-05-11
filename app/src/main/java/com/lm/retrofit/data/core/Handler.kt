package com.lm.retrofit.data.core

import com.lm.retrofit.data.core.Callback.startRequest
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call

object Handler {
	fun <T> request(call: Call<T>) =
		callbackFlow {
			call.startRequest { trySendBlocking(it) }
			awaitClose()
		}.flowOn(IO)
}