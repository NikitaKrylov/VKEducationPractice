package com.example.vknext.ui.uikit

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    icon: Painter? = null
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        enabled = isEnabled,
        modifier = Modifier
            .then(modifier)
    ) {
        Row {
            icon?.let {
                Icon(
                    painter = it,
                    contentDescription = null,
                    tint = contentColor,
                )
            }
            Text(
                text = text,
                color = contentColor,
            )
        }
    }

}

@Preview
@Composable
private fun PrimaryButtonPreview() {
    PrimaryButton(
        text = "Text",
        onClick = {},
    )
}