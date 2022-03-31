package com.lm.retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lm.retrofit.di.MainDependencies
import com.lm.retrofit.ui.screens.Anime
import com.lm.retrofit.ui.screens.Memes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { MainDependencies {
            Anime()
        //    Memes()
        } }
    }
}