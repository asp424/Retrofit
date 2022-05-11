package com.lm.retrofit.data.mapper

import com.lm.core.Mapper
import com.lm.core.Resource
import com.lm.retrofit.data.model.AnimeRequest
import com.lm.retrofit.data.model.AnimeModel
import okhttp3.ResponseBody

interface AnimeMapper : Mapper.DataToUI<AnimeRequest, Resource<List<AnimeModel>>> {
	class Base : AnimeMapper {
		override fun map(data: AnimeRequest): Resource<List<AnimeModel>> =
			Resource.Success(data.results)
	}
	
	override fun map(throwable: Throwable): Resource<List<AnimeModel>> =
		Resource.Failure(throwable)
	
	override fun map(error: ResponseBody?): Resource<List<AnimeModel>> =
		Resource.Exception(error)
	
	override fun map(): Resource<List<AnimeModel>> = Resource.Loading
}
