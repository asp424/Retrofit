package com.lm.retrofit.data.memes.retrofit

import com.lm.retrofit.data.memes.api.MemesApi
import com.lm.retrofit.data.memes.api.MemesApi.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MemesApi::class.java)
    }
}