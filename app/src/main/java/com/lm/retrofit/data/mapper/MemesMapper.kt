package com.lm.retrofit.data.mapper

import com.lm.core.Mapper
import com.lm.core.Resource
import com.lm.retrofit.data.model.MemesModel
import com.lm.retrofit.data.model.MemesRequest
import okhttp3.ResponseBody

interface MemesMapper : Mapper.DataToUI<MemesRequest, Resource<List<MemesModel>>> {
	
	class Base() : MemesMapper {
		override fun map(data: MemesRequest): Resource<List<MemesModel>> {
			mutableListOf<MemesModel>().apply {
				data.data?.get("memes")?.asJsonArray?.forEach {
					it.asJsonObject.apply {
						add(
							MemesModel(
								id = get("id").asString,
								name = get("name").asString,
								url = get("url").asString,
								width = get("width").asInt,
								height = get("height").asInt,
								box_count = get("box_count").asInt
							)
						)
					}
				}
				return Resource.Success(this)
			}
		}
			
			override fun map(throwable: Throwable): Resource<List<MemesModel>> =
				Resource.Failure(throwable)
			
			override fun map(error: ResponseBody?): Resource<List<MemesModel>> =
				Resource.Exception(error)
			
			override fun map(): Resource<List<MemesModel>> = Resource.Loading
		}
	}

