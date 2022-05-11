package com.lm.retrofit.data.core

import com.lm.core.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Callback {
	inline fun <T> Call<T>.startRequest(crossinline onResult: (response: Resource<T>) -> Unit) {
		enqueue(object : Callback<T> {
			override fun onResponse(call: Call<T>, response: Response<T>) {
				response.body()?.apply {
					if (response.isSuccessful) onResult(Resource.Success(this))
					else onResult(Resource.Exception(response.errorBody()))
				}
			}
			
			override fun onFailure(call: Call<T>, throwable: Throwable) {
				onResult(Resource.Failure(throwable))
			}
		})
	}
}