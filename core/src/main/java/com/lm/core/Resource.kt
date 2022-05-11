package com.lm.core

import okhttp3.ResponseBody

sealed class Resource<out T> : Object<T> {
	
	data class Success<T>(val data: T) : Resource<T>() {
		override fun <U> map(mapper: Mapper.DataToUI<in T, U>): U = mapper.map(data)
	}
	
	data class Exception<T>(val error: ResponseBody?) : Resource<T>() {
		override fun <U> map(mapper: Mapper.DataToUI<in T, U>): U =
			mapper.map(error)
	}
	
	data class Failure<T>(val throwable: Throwable) : Resource<T>() {
		override fun <U> map(mapper: Mapper.DataToUI<in T, U>): U =
			mapper.map(throwable)
	}
	
	object Loading : Resource<Nothing>() {
		override fun <U> map(mapper: Mapper.DataToUI<in Nothing, U>): U = mapper.map()
	}
}