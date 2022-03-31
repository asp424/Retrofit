package com.lm.retrofit.ui.cells

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier

@Composable
fun ColFMS(content: @Composable (ColumnScope) -> Unit) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = CenterHorizontally,
    verticalArrangement = Center
        ) {
        content(this)
    }
}