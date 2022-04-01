package com.lm.retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.PeopleAlt
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.lm.retrofit.di.MainDependencies
import com.lm.retrofit.ui.screens.Anime
import com.lm.retrofit.ui.screens.Memes

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainDependencies {
                var cont by rememberSaveable { mutableStateOf(true) }
                Scaffold(
                    bottomBar = {
                        BottomNavigation {
                            BottomNavigationItem(
                                selected = cont,
                                onClick = { cont = true },
                                label = {
                                    Text(
                                        text = "Anime",
                                        style = TextStyle(fontSize = 10.sp)
                                    )
                                },
                                icon = {
                                    Icon(Icons.Default.Palette, "ass")
                                }
                            )
                            BottomNavigationItem(
                                selected = !cont,
                                onClick = { cont = false },
                                label = {
                                    Text(
                                        text = "Memes",
                                        style = TextStyle(fontSize = 10.sp)
                                    )
                                },
                                icon = {
                                    Icon(Icons.Default.Mood, "ass")
                                }, modifier = Modifier
                                    .fillMaxSize()
                            )
                        }
                    }, content = {
                        if (cont) Anime()
                        else Memes()
                    })
            }
        }
    }
}