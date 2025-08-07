package com.example.vknext.ui.uikit.snackbar

import androidx.annotation.DrawableRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class SnackbarColors(
    val containerColor: Color,
    val textColor: Color,
    val iconTint: Color,
) {
    companion object {
        @Composable
        fun default() = SnackbarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            textColor = MaterialTheme.colorScheme.onSurface,
            iconTint = MaterialTheme.colorScheme.primary,
        )
    }
}


sealed interface SnackbarStyle {

    val iconResId: Int?

    @Composable
    fun colors(): SnackbarColors

    data class Success(
        override val iconResId: Int? = null
    ) : SnackbarStyle {

        @Composable
        override fun colors(): SnackbarColors = SnackbarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            textColor = MaterialTheme.colorScheme.onSurface,
            iconTint = MaterialTheme.colorScheme.primary,
        )
    }

    data object Error : SnackbarStyle {
        @DrawableRes override val iconResId: Int? = null

        @Composable
        override fun colors(): SnackbarColors = SnackbarColors(
            containerColor = MaterialTheme.colorScheme.errorContainer,
            textColor = MaterialTheme.colorScheme.onSurface,
            iconTint = MaterialTheme.colorScheme.onSurface,
        )

    }

}


