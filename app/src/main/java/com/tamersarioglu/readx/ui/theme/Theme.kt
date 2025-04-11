package com.tamersarioglu.readx.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

// Keeping the default DarkColorScheme for now, can be customized later
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryGreen,
    secondary = SecondaryAmber,
    background = Color(0xFF1C1B1F),
    surface = Color(0xFF2C2B2F),
    onPrimary = SurfaceIvory,
    onSecondary = TextDarkBrown,
    onBackground = Color(0xFFE6E1E5),
    onSurface = Color(0xFFE6E1E5)
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryGreen,
    secondary = SecondaryAmber,
    background = BackgroundBrown,
    surface = SurfaceIvory,
    onPrimary = SurfaceIvory,
    onSecondary = TextDarkBrown,
    onBackground = TextDarkBrown,
    onSurface = TextDarkBrown
)

@Composable
fun ReadXTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}