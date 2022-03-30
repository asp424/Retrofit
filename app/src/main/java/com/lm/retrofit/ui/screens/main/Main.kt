package com.lm.retrofit.ui.screens.main

import android.util.Log
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.gson.JsonObject
import com.lm.retrofit.core.APIResponse
import com.lm.retrofit.di.MainDep

@Composable
fun Main() {
    MainDep.depends.responseViewModel.also { vm ->
        val memes by vm.memes.collectAsState()
        LocalLifecycleOwner.current.lifecycle.addObserver(MainDep.depends.responseViewModel)
        when (memes) {
            is APIResponse.Success ->
                LazyColumn(
                    content = {
                        items(
                            (memes as APIResponse.Success<JsonObject>)
                                .data?.get("data")?.asJsonObject?.get("memes")?.asJsonArray!!.toList()
                        ) {
                            AsyncImage(
                                model = it.asJsonObject["url"].asString,
                                contentDescription = null, modifier = Modifier.padding(bottom = 20.dp)
                            )
                        }
                    }, modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Center
                )

            is APIResponse.Loading -> Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Center
            ) {
                CircularProgressIndicator()
            }

            is APIResponse.Failure -> Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Center
            ) {
                Text(text = (memes as APIResponse.Failure<JsonObject>).message)
            }
        }
    }
}