package com.lm.retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.lm.retrofit.di.MainDep.depends
import com.lm.retrofit.di.MainDependencies
import com.lm.retrofit.ui.screens.main.Main

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      setContent { MainDependencies { Main() } } }
}