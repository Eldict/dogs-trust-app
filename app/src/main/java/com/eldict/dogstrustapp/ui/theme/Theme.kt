package com.eldict.dogstrustapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Connects the sunset colours to Material 3 colour roles (primary, background, surface).
// Screens use the roles, so no screen hard-codes a colour.
private val SunsetColorScheme = lightColorScheme(
    primary = SunsetOrange,
    onPrimary = Color.White,
    primaryContainer = SunsetOrangeLight,
    onPrimaryContainer = DeepBrown,
    secondary = SunsetPurple,
    onSecondary = Color.White,
    secondaryContainer = SunsetPurpleLight,
    onSecondaryContainer = DeepBrown,
    tertiary = SunsetPink,
    onTertiary = Color.White,
    background = WarmCream,
    onBackground = DeepBrown,
    surface = WarmCream,
    onSurface = DeepBrown,
    surfaceVariant = SunsetOrangeLight,
    surfaceContainerHighest = SunsetOrangeLight
)

// Wraps every screen so they all use this colour scheme.
@Composable
fun DogsTrustAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = SunsetColorScheme,
        typography = Typography,
        content = content
    )
}