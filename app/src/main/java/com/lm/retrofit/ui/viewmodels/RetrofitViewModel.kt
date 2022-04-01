package com.lm.retrofit.ui.viewmodels

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.lm.retrofit.data.api.APIResponse
import com.lm.retrofit.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RetrofitViewModel(private val repository: Repository) : ViewModel(),
    DefaultLifecycleObserver {

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)

        if (_memes.value !is APIResponse.Success) fetchMemes()
        if (_anime.value !is APIResponse.Success) fetchAnime()
    }

    private val scope by lazy { viewModelScope }

    private fun fetchMemes() =
        scope.launch { repository.memes().collect { _memes.value = it } }

    private fun fetchAnime() =
        scope.launch {
            repository.anime().collect { _anime.value = it }
        }

    private val _memes: MutableStateFlow<APIResponse<JsonObject>> =
        MutableStateFlow(APIResponse.Loading)
    val memes get() = _memes.asStateFlow()

    private val _anime: MutableStateFlow<APIResponse<JsonObject>> =
        MutableStateFlow(APIResponse.Loading)
    val anime get() = _anime.asStateFlow()
}