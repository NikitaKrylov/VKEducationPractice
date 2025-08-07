package com.example.vknext.ui.feedback.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vknext.R
import com.example.vknext.ui.feedback.FeedBackListener
import com.example.vknext.ui.feedback.state.FeedBackState
import com.example.vknext.ui.login.state.AccountItem
import com.example.vknext.ui.uikit.Pannel

@Composable
fun FeedBack(
    state: FeedBackState.ShowFeedBack,
    actions: FeedBackListener,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ) {
        var isPanelVisible by remember { mutableStateOf(true) }

        TextField(
            value = state.search,
            onValueChange = actions::onSearchTextChanged,
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            maxLines = 1,
            placeholder = {
                Text("Поиск по колегам...")
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFF818C99),
                unfocusedContainerColor = Color(0xFFEBEDF0),
                focusedContainerColor = Color(0xFFEBEDF0),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
        )

        AnimatedVisibility(
            visible = isPanelVisible
        ) {
            Pannel(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_cross),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(16.dp)
                        .align(Alignment.TopEnd)
                        .clickable {
                            isPanelVisible = false
                        }
                )
                Text(
                    text = "Выберите человека, которому вы хотите отправить сообщение",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                )
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.searchItems) { item ->
                PersonItem(
                    avatarRes = item.iconRes,
                    title = item.name,
                    description = item.role,
                    onClick = {
                        actions.onItemClick(item.id)
                    },
                    trailingIconResId = R.drawable.ic_star,
                    modifier = Modifier
                        .padding(12.dp)
                )
                HorizontalDivider()
            }
        }
    }
}


@Composable
fun PersonItem(
    title: String,
    description: String?,
    avatarRes: Int,
    onClick: () -> Unit,
    trailingIconResId: Int,
    modifier: Modifier = Modifier,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .clickable {
                onClick()
            }
            .fillMaxWidth()
            .then(modifier)
    ) {
        Image(
            painter = painterResource(avatarRes),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(46.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            )

            description?.let {
                Text(
                    text = it,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }

        Icon(
            painter = painterResource(trailingIconResId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(32.dp)
        )
    }
}


@Preview
@Composable
private fun FeedBackPreview() {
    FeedBack(
        actions = object : FeedBackListener {
            override fun onItemClick(accountId: Long) {
                TODO("Not yet implemented")
            }

            override fun onSearchTextChanged(value: String) {
                TODO("Not yet implemented")
            }

        },
        state = FeedBackState.ShowFeedBack(
            searchItems = List(10) {
                AccountItem(
                    id = it.toLong(),
                    name = LoremIpsum(2).values.joinToString(),
                    isSelected = false,
                    iconRes = R.drawable.avatar1,
                    role = LoremIpsum(4).values.joinToString(),
                )
            }
        )
    )
}