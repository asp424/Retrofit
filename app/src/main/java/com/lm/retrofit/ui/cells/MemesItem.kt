package com.lm.retrofit.ui.cells

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lm.retrofit.data.model.MemesModel
import com.lm.retrofit.di.MainDep.depends

@Composable
fun MemesItem(mem: MemesModel) {
	LocalDensity.current.apply {
		depends.apply {
			var loadSuccess by remember { mutableStateOf(false) }
			var full by remember { mutableStateOf(false) }
			
			mem.apply {
				AsyncImage(
					model = url,
					contentDescription = null,
					modifier =
					Modifier
						.padding(10.dp)
						// .clickable { full = !full }
						.size(
							animateDpAsState(
								if (full) screenWidth else
									width.toDp()
							).value, animateDpAsState(
								if (full) screenHeight / 2 else height.toDp()
							).value
						),
					onSuccess = {
						loadSuccess = true
					}, contentScale = ContentScale.Crop
				)
				
				if (loadSuccess)
					Text(
						text = name,
						modifier =
						Modifier.padding(bottom = 20.dp)
					)
			}
		}
	}
}

