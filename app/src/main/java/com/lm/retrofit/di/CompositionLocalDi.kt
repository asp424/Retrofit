package com.lm.retrofit.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import com.lm.retrofit.MainActivity
import com.lm.retrofit.data.memes.repository.MemesRepository
import com.lm.retrofit.data.memes.retrofit.RetrofitInstance
import com.lm.retrofit.ui.viewmodels.ResponseViewModel
import com.lm.retrofit.ui.viewmodels.factorys.ResponseViewModelFactory

data class Main(
    val responseViewModel: ResponseViewModel
)

val LocalMainDependencies = staticCompositionLocalOf<Main> { error("No value provided") }

object MainDep {
    val depends: Main @Composable get() = LocalMainDependencies.current
}

@Composable
fun MainDependencies(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalMainDependencies provides Main(
            responseViewModel = ViewModelProvider(
                LocalContext.current as MainActivity,
                ResponseViewModelFactory(MemesRepository.Base(RetrofitInstance.api))
            )[ResponseViewModel::class.java]
        ), content = content
    )
}




