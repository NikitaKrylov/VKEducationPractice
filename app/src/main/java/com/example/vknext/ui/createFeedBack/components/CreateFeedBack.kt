package com.example.vknext.ui.createFeedBack.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.CheckResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils
import com.example.vknext.R
import com.example.vknext.ui.createFeedBack.CreateFeedbackListener
import com.example.vknext.ui.createFeedBack.state.CreateFeedBackState
import com.example.vknext.ui.createFeedBack.state.FeedBackGroupItem
import com.example.vknext.ui.createFeedBack.state.PointItem
import com.example.vknext.ui.uikit.Pannel
import com.example.vknext.ui.uikit.PrimaryButton
import kotlin.random.Random

@Composable
fun CreateFeedBack(
    state: CreateFeedBackState,
    actions: CreateFeedbackListener,
) {

    val isValid = remember(state) {
        state.groups.map {
            it.points.map { it.isSelected }.any {it}
        }.any{it}
    }

    val isEnabledSelection = remember(state) {
        state.groups.sumOf { group ->
            group.points.count { point -> point.isSelected }
        } < 3
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        item {
            Pannel(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Можно выбрать до 3х пунктов. \nКоллега узнает, что именно вы отметили его работу.",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }

        items(state.groups) { group ->
            Group(
                tag = group.hashtag,
                comments = group.points,
                onPointChange = actions::updatePointItem,
                isEnabled = isEnabledSelection,
            )
        }
        item {
            PrimaryButton(
                text = "Сохранить",
                onClick = actions::saveForm,
                isEnabled = isValid,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun Group(
    tag: String,
    comments: List<PointItem>,
    onPointChange: (Long, Boolean) -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
) {
    Column(
        modifier = Modifier
            .then(modifier)
    ) {
        Text(
            text = "#$tag",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        comments.forEach { comment ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
            ) {
                Checkbox(
                    checked = comment.isSelected,
                    onCheckedChange = { selected ->
                        if (isEnabled || selected.not())
                            onPointChange(comment.id, selected)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.primary.takeIf {
                            isEnabled
                        } ?: Color.Gray
                    ),
                )
                Text(
                    text = comment.text
                )
            }

        }
    }


}

@Preview
@Composable
private fun CreateFeedBackPreview() {
    CreateFeedBack(
        actions = object : CreateFeedbackListener{
            override fun updatePointItem(pointId: Long, isSelected: Boolean) {
                TODO("Not yet implemented")
            }

            override fun saveForm() {
                TODO("Not yet implemented")
            }

        },
        state = CreateFeedBackState(
            groups = List(7) {
                FeedBackGroupItem(
                    hashtag = LoremIpsum(3).values.joinToString(),
                    points = List(4) {
                        PointItem(
                            text = LoremIpsum(4).values.joinToString(),
                            isSelected = Random.nextBoolean(),
                            id = Random.nextLong()
                        )
                    }
                )
            }
        )
    )
}