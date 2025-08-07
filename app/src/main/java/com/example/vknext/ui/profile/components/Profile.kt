package com.example.vknext.ui.profile.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vknext.ui.profile.state.ProfileState
import com.example.vknext.R
import com.example.vknext.core.data.models.Answer
import com.example.vknext.core.data.models.FeedBackPoint
import com.example.vknext.domain.AnswerWithSender
import com.example.vknext.ui.profile.ProfileActionsListener
import com.example.vknext.ui.uikit.Pannel
import com.example.vknext.ui.uikit.PrimaryButton

@Composable
fun Profile(
    state: ProfileState.ShowProfile,
    actions: ProfileActionsListener,
) {
    var isPanelVisible by remember { mutableStateOf(true) }

    Column(
        verticalArrangement = Arrangement.spacedBy(25.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .verticalScroll(rememberScrollState())
    ) {

        AccountInfoPanel(
            iconRes = state.avatarId ?: R.drawable.ic_profile,
            title = state.username,
            subtitle = state.role,
        )

        HorizontalDivider()

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
                    text = "Здесь будет отображаться статистика и информация. \nВы можете просмотреть все сообщения, которые вам прислали",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                )
            }
        }

        FeedBackCounter(
            iconRes = R.drawable.ic_star,
            title = "Благодарности",
            leftCount = state.totalThanks.toString(),
            leftTitle = "За прошлую неделю",
            rightCount = state.totalThanks.toString(),
            rightTitle = "За все время",
            modifier = Modifier
                .height(120.dp)
        )

        HorizontalDivider()

        if (state.thanks.isNotEmpty()) {
            AnswersList(
                answers = state.thanks,
            )
        } else {
            Text(
                text = "Нет данных",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = .7f)
            )
        }

        HorizontalDivider()


        PrimaryButton(
            text = "Выйти",
            onClick = actions::onLogOut,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}




@Composable
fun AccountInfoPanel(
    iconRes: Int,
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .then(modifier)
    ) {
        Image(
            painter = painterResource(iconRes),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(64.dp)
        )

        Column {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            )

            subtitle?.let {
                Text(
                    text = it,
                    fontSize = 20.sp
                )
            }
        }
    }
}


@Composable
fun AnswersList(
    answers: List<AnswerWithSender>,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .then(modifier)
    ) {
        answers.forEach { answer ->
            AnswerItem(
                avatarId = answer.sender?.iconRes ?: R.drawable.ic_profile,
                name = answer.sender?.let {
                    "${it.firstName} ${it.lastName}"
                } ?: "Unknown",
                points = answer.points,
            )
        }
    }
}


@Composable
fun AnswerItem(
    avatarId: Int,
    name: String,
    points: List<FeedBackPoint>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(avatarId),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(46.dp)
            )

            Text(
                text = name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            )
        }

        Column {
            points.forEach { point ->
                Row {
                    Text(text = "\u2022 \t\t")
                    Text(text = point.name)
                }
            }
        }


    }
}


@Composable
fun FeedBackCounter(
    iconRes: Int,
    title: String,
    leftCount: String,
    leftTitle: String,
    rightCount: String,
    rightTitle: String,
    modifier: Modifier = Modifier,
    iconTint: Color = MaterialTheme.colorScheme.primary
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .then(modifier)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier
                    .size(32.dp)
            )
            Text(
                text = title,
                fontSize = 24.sp
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        ) {
            InfoBlock(
                annotation = leftTitle,
                count = leftCount,
                modifier = Modifier.aspectRatio(2f),
            )

            VerticalDivider(
                modifier = Modifier
            )

            InfoBlock(
                annotation = rightTitle,
                count = rightCount,
                modifier = Modifier.aspectRatio(2f),

            )

        }
    }
}

@Composable
fun InfoBlock(
    count: String,
    annotation: String,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .then(modifier)
    ) {
        Text(
            text = count,
            fontSize = 21.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface,

        )
        Text(
            text = annotation,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}


@Preview
@Composable
private fun ProfilePreview() {
    Profile(
        state = ProfileState.ShowProfile(
            username = "Username",
            role = "Role",
            avatarId = -1,
            thanks = emptyList(),
            totalThanks = 12,
            todayThanks = 1,
        ),
        actions = object : ProfileActionsListener {
            override fun onLogOut() {
                TODO("Not yet implemented")
            }

        }
    )
}


//@Preview
//@Composable
//private fun InfoBlockPreview() {
//    FeedBackCounter(
//        modifier = Modifier
//            .height(120.dp)
//    )
//}