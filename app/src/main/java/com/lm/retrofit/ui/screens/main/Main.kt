package com.lm.retrofit.ui.screens.main

import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Arrangement.Top
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.gson.JsonObject
import com.lm.retrofit.data.memes.api.APIResponse
import com.lm.retrofit.di.MainDep.depends
import com.lm.retrofit.ui.cells.ColFMS

@Composable
fun Main() {
    depends.apply {
        responseViewModel.also { vm ->
            memesMapper.also { mapper ->

                val memes by vm.memes.collectAsState()
                LocalLifecycleOwner.current.lifecycle.addObserver(vm)

                when (memes) {
                    is APIResponse.Success ->
                        LazyColumn(
                            content = {
                                items(
                                    mapper.map((memes as APIResponse.Success<JsonObject>).data)
                                ) {
                                    Column(
                                        horizontalAlignment = CenterHorizontally,
                                        verticalArrangement = Center
                                    ) {
                                        it.asJsonObject.apply {
                                            AsyncImage(
                                                model = get("url").asString,
                                                contentDescription = null, modifier =
                                                Modifier.padding(10.dp)
                                            )
                                            Text(
                                                text = get("name").asString, modifier =
                                                Modifier.padding(bottom = 20.dp)
                                            )
                                        }
                                    }
                                }
                            }, modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = CenterHorizontally,
                            verticalArrangement = Top
                        )

                    is APIResponse.Loading -> ColFMS { CircularProgressIndicator() }

                    is APIResponse.Failure -> ColFMS {
                        Text(text = (memes as APIResponse.Failure<JsonObject>).message)
                    }

                    is APIResponse.Exception -> ColFMS {
                        Text(text = (memes as APIResponse.Failure<JsonObject>).message)
                    }
                }
            }
        }
    }
}