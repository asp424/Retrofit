package com.lm.retrofit.ui.viewmodels.factorys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lm.retrofit.data.memes.repository.MemesRepository
import com.lm.retrofit.ui.viewmodels.ResponseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

class ResponseViewModelFactory(
    private val memesRepository: MemesRepository
) : ViewModelProvider.Factory {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        ResponseViewModel(memesRepository) as T
}
