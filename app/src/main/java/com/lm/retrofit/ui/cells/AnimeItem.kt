package com.lm.retrofit.ui.cells

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lm.retrofit.data.model.AnimeModel
import com.lm.retrofit.di.MainDep


@Composable
fun AnimeItem(anime: AnimeModel) {
    LocalDensity.current.apply {
        MainDep.depends.apply {
            var loadSuccess by remember { mutableStateOf(false) }
            var full by remember { mutableStateOf(false) }

            anime.apply {
                AsyncImage(
                    model = image_url,
                    contentDescription = null,
                    modifier =
                    Modifier
                        .padding(10.dp)
                        .size(screenWidth - 60.dp)
                        .clickable { full = !full },
                    onSuccess = {
                        loadSuccess = true
                    }
                )

                if (loadSuccess) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 40.dp),
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Text(
                            text = title,
                            modifier =
                            Modifier.padding(bottom = 6.dp, start = 10.dp, end = 10.dp), textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Rate: $score",
                            modifier =
                            Modifier.padding(bottom = 6.dp, start = 10.dp, end = 10.dp), textAlign = TextAlign.Center
                        )

                        Text(
                            text = synopsis,
                            modifier =
                            Modifier.padding(bottom = 6.dp, start = 10.dp, end = 10.dp), textAlign = TextAlign.Center
                        )
                        LocalContext.current.apply {
                            Text(
                                text = url,
                                modifier =
                                Modifier
                                    .padding(bottom = 6.dp, start = 10.dp, end = 10.dp)
                                    .clickable {
                                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                                    },
                                textAlign = TextAlign.Center,
                                color = Blue,
                                textDecoration = TextDecoration.combine(
                                    listOf(TextDecoration.Underline)
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

