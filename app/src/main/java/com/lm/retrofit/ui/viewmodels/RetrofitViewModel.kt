package com.lm.retrofit.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lm.core.Resource
import com.lm.retrofit.data.mapper.AnimeMapper
import com.lm.retrofit.data.mapper.MemesMapper
import com.lm.retrofit.data.model.AnimeModel
import com.lm.retrofit.data.model.MemesModel
import com.lm.retrofit.data.repository.Repository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class RetrofitViewModel(
	private val repository: Repository,
	private val memesMapper: MemesMapper,
	private val animeMapper: AnimeMapper
) : ViewModel() {
	
	@ExperimentalCoroutinesApi
	val fetchMemes by lazy {
		MutableSharedFlow<Resource<List<MemesModel>>>(
			1, 1,
			BufferOverflow.DROP_LATEST
		).apply {
			viewModelScope.launch {
				repository.memes().flowOn(IO)
					.flatMapLatest {
						flowOf(it.map(memesMapper)) }
					.collect { this@apply.emit(it) }
			}
		}
	}
	
	@ExperimentalCoroutinesApi
	val fetchAnime by lazy {
		MutableSharedFlow<Resource<List<AnimeModel>>>(
			1, 1,
			BufferOverflow.DROP_LATEST
		).apply {
			viewModelScope.launch {
				repository.anime().flowOn(IO)
					.flatMapLatest { flowOf(it.map(animeMapper)) }
					.collect {
						this@apply.emit(it)
					}
			}
		}
	}
}