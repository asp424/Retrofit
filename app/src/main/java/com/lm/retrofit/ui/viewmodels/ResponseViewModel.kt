package com.lm.retrofit.ui.viewmodels

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.lm.retrofit.data.memes.api.APIResponse
import com.lm.retrofit.data.memes.repository.MemesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ResponseViewModel(private val memesRepository: MemesRepository) : ViewModel(),
    DefaultLifecycleObserver {

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)

        viewModelScope.launch { memesRepository.memes().collect { _memes.value = it } }
    }

    private val _memes: MutableStateFlow<APIResponse<JsonObject>> =
        MutableStateFlow(APIResponse.Loading)
    val memes get() = _memes.asStateFlow()
}