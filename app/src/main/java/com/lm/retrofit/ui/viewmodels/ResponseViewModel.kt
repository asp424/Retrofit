package com.lm.retrofit.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.lm.retrofit.data.repository.Repository

class ResponseViewModel(private val repository: Repository) : ViewModel() {

    fun fetchMemes() = repository.memes()

    fun fetchAnime() = repository.anime()
}