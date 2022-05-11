package com.lm.retrofit.ui.screens

import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Arrangement.Top
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import com.lm.core.Resource
import com.lm.retrofit.di.MainDep.depends
import com.lm.retrofit.ui.cells.ColFMS
import com.lm.retrofit.ui.cells.MemesItem
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun Memes() {
	depends.responseViewModel.apply {
		fetchMemes.collectAsState(Resource.Loading).value.apply {
			when (this) {
				is Resource.Success -> {
					LazyColumn(
						content = {
							items(data) {
								Column(
									horizontalAlignment = CenterHorizontally,
									verticalArrangement = Center
								) { MemesItem(it) }
							}
						}, modifier = Modifier.fillMaxSize(),
						horizontalAlignment = CenterHorizontally,
						verticalArrangement = Top
					)
				}
				
				is Resource.Loading -> ColFMS { CircularProgressIndicator() }
				
				is Resource.Failure -> ColFMS {
					Text(
						text = throwable.localizedMessage ?: "Error",
						textAlign = TextAlign.Center
					)
				}
				
				is Resource.Exception -> ColFMS {
					Text(text = error.toString(), textAlign = TextAlign.Center)
				}
			}
		}
	}
}


