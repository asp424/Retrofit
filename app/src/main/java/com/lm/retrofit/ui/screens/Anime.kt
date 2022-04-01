package com.lm.retrofit.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import com.lm.retrofit.data.api.APIResponse
import com.lm.retrofit.di.MainDep.depends
import com.lm.retrofit.ui.cells.AnimeItem
import com.lm.retrofit.ui.cells.ColFMS

@Composable
fun Anime() {
    depends.apply {
        responseViewModel.also { vm ->
            animeMapper.also { mapper ->
                LocalLifecycleOwner.current.lifecycle.addObserver(vm)
                vm.anime.collectAsState(APIResponse.Loading).value.also { res ->
                    when (res) {
                        is APIResponse.Success -> {
                            mapper.map(res.data).also { list ->
                                LazyColumn(
                                    content = {
                                        items(list) {
                                            Column(
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ) { AnimeItem(it) }
                                        }
                                    }, modifier = Modifier.fillMaxSize(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Top
                                )
                            }
                        }

                        is APIResponse.Loading -> ColFMS { CircularProgressIndicator() }

                        is APIResponse.Failure -> ColFMS {
                            Text(text = res.message, textAlign = TextAlign.Center)
                        }

                        is APIResponse.Exception -> ColFMS {
                            Text(text = res.message!!, textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }
    }
}