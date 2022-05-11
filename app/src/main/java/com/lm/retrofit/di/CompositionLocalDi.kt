package com.lm.retrofit.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.lm.retrofit.MainActivity
import com.lm.retrofit.data.mapper.AnimeMapper
import com.lm.retrofit.data.mapper.MemesMapper
import com.lm.retrofit.data.repository.Repository
import com.lm.retrofit.data.retrofit.RetrofitInstances.animeApi
import com.lm.retrofit.data.retrofit.RetrofitInstances.memesApi
import com.lm.retrofit.ui.viewmodels.RetrofitViewModel
import com.lm.retrofit.ui.viewmodels.factorys.ResponseViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi

data class Main @ExperimentalCoroutinesApi constructor(
    val responseViewModel: RetrofitViewModel,
    val screenWidth: Dp,
    val screenHeight: Dp,
)

val LocalMainDependencies = staticCompositionLocalOf<Main> { error("No value provided") }

object MainDep {
    val depends: Main @Composable get() = LocalMainDependencies.current
}

@ExperimentalCoroutinesApi
@Composable
fun MainDependencies(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalMainDependencies provides Main(
            responseViewModel = ViewModelProvider(
                LocalContext.current as MainActivity,
                ResponseViewModelFactory(Repository.Base(),
                    AnimeMapper.Base(), MemesMapper.Base()
                    )
            )[RetrofitViewModel::class.java],
            
            screenWidth = LocalConfiguration.current.screenWidthDp.dp,

            screenHeight = LocalConfiguration.current.screenHeightDp.dp

        ), content = content
    )
}




