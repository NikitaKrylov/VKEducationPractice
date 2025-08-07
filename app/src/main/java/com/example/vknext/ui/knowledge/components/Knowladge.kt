package com.example.vknext.ui.knowledge.components

import android.R.attr.topOffset
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.withSaveLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vknext.ui.knowledge.state.KnowladgeItem
import com.example.vknext.ui.knowledge.state.KnowladgeListener
import com.example.vknext.ui.knowledge.state.KnowladgeState
import com.example.vknext.R
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

@Composable
fun Knowladge(
    state: KnowladgeState,
    actions: KnowladgeListener,
) {

    val largeRadialGradient = Brush.verticalGradient(
        listOf(
            Color(0xFF89D4CF),
            Color(0xFF12FFF7),
//            Color(0xFFFF6FD8),
            Color(0xFF734AE8),
        )
    )

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .drawWithContent {
                with(drawContext.canvas) {
                    withSaveLayer(
                        bounds = size.toRect(),
                        paint = Paint()
                    ) {
                        drawContent()
                        drawRect(
                            brush = largeRadialGradient,
                            blendMode = BlendMode.SrcAtop,
                        )
                    }
                }
            }
    ) {
        state.items.forEach { item ->
            KnowladgeItem(
                item = item,
                onClick = actions::onItemClick,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}


@Composable
fun KnowladgeItem(
    item: KnowladgeItem,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .clickable {
                onClick(item.id)
            }
            .then(modifier)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_key),
            contentDescription = item.title,
            modifier = Modifier
                .size(37.dp)
        )

        Text(
            text = item.title,
            overflow = TextOverflow.Ellipsis,
            fontSize = 40.sp,
            color = Color.White,
        )
    }
}

@Preview
@Composable
private fun KnowladgePreveiw() {
    Knowladge(
        state = KnowladgeState(
            items = List(10) {
                KnowladgeItem(
                    id = Random.nextInt(),
                    title = LoremIpsum(Random.nextInt(1, 3))
                        .values
                        .shuffled()
                        .joinToString(),
                )
            }
        ),
        actions = object : KnowladgeListener {
            override fun onItemClick(id: Int) {
                TODO("Not yet implemented")
            }

        }
    )
}