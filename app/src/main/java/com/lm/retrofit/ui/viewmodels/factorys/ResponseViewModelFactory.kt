package com.lm.retrofit.ui.viewmodels.factorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lm.retrofit.data.mapper.AnimeMapper
import com.lm.retrofit.data.mapper.MemesMapper
import com.lm.retrofit.data.repository.Repository
import com.lm.retrofit.ui.viewmodels.RetrofitViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

class ResponseViewModelFactory(
    private val memesRepository: Repository,
    private val animeMapper: AnimeMapper,
    private val memesMapper: MemesMapper
) : ViewModelProvider.Factory {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        RetrofitViewModel(memesRepository, memesMapper, animeMapper) as T
}
