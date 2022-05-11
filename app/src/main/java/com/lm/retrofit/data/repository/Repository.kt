package com.lm.retrofit.data.repository

import com.lm.core.Resource
import com.lm.retrofit.data.api.AnimeApi
import com.lm.retrofit.data.core.Handler.request
import com.lm.retrofit.data.api.MemesApi
import com.lm.retrofit.data.model.AnimeRequest
import com.lm.retrofit.data.model.MemesRequest
import com.lm.retrofit.data.retrofit.RetrofitInstances.animeApi
import com.lm.retrofit.data.retrofit.RetrofitInstances.memesApi
import kotlinx.coroutines.flow.Flow

interface Repository {
	
	fun memes(): Flow<Resource<MemesRequest>>
	
	fun anime(): Flow<Resource<AnimeRequest>>
	
	class Base() : Repository {
		
		override fun memes() = request(memesApi.fetchMemes())
		
		override fun anime() = request(animeApi.fetchAnime())
		
	}
}