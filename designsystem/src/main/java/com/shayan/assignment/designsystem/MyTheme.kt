package com.shayan.assignment.designsystem

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun MyTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = myColors, typography = myTypography) {
        content()
    }
}
