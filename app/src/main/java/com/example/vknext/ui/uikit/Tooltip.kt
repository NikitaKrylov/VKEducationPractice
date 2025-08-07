package com.example.vknext.ui.uikit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp


@Composable
fun Tooltip(
    title: String,
    text: String,
    modifier: Modifier = Modifier,
    confirmAction: Pair<String, () -> Unit>? = null,
    declineAction: Pair<String, () -> Unit>? = null,
    background: Color = Color(0xFF2688EB)
) {
    Box(
        modifier = Modifier
            .then(modifier)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(background)
                .padding(20.dp)
                .align(Alignment.TopStart)
        ) {
            Text(
                text = title,
            )

            Text(
                text = text,
            )

            Row {
                confirmAction?.let { action ->
                    Text(
                        text = action.first,
                        modifier = Modifier.clickable {
                            action.second()
                        }
                    )
                }

                declineAction?.let { action ->
                    Text(
                        text = action.first,
                        modifier = Modifier.clickable {
                            action.second()
                        }
                    )
                }
            }
        }
    }

}

@Preview
@Composable
private fun TooltipPreview() {
    Tooltip(
        title = LoremIpsum(3).values.joinToString(),
        text = LoremIpsum(13).values.joinToString(),
        confirmAction = "Ok" to {}
    )
}