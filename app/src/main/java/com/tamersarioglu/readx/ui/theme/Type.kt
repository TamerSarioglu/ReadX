package com.tamersarioglu.readx.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// New ReadX Typography with fantasy-genre fonts
val ReadXTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Serif, // Using Serif for a more classic/fantasy feel
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Serif, // Using Serif
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default, // Keeping Default for body text readability
        fontSize = 16.sp,
        lineHeight = 24.sp
    )
    // Define other styles like bodyMedium, labelSmall etc. as needed
)

// Rename to Typography to match MaterialTheme expectation
val Typography = ReadXTypography