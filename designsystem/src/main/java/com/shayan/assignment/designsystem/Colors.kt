package com.shayan.assignment.designsystem

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

private val purple_200 = Color(0xFFBB86FC)
private val purple_500 = Color(0xFF6200EE)
private val purple_700 = Color(0xFF3700B3)
private val teal_200 = Color(0xFF03DAC5)
private val teal_700 = Color(0xFF018786)
private val black = Color(0xFF000000)
private val dark1 = Color(0xFF141414)
private val dark2 = Color(0xFF292929)
private val dark3 = Color(0xFF595959)
private val white = Color.White

private val purple_gray = Color(0xFFE8E0FF)

val myColors = lightColors(
    primary = purple_500,
    secondary = teal_200,
    surface = purple_200,
    onSurface = white,
    primaryVariant = purple_700
)
