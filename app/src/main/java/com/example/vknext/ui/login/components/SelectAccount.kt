package com.example.vknext.ui.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vknext.ui.login.LoginListener
import com.example.vknext.ui.login.state.AccountItem
import com.example.vknext.ui.login.state.AuthType
import com.example.vknext.ui.login.state.LoginState
import com.example.vknext.R

@Composable
fun SelectAccount(
    state: LoginState,
    actions: LoginListener,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .then(modifier)
    ) {
        item {
            Text(
                text = "Кто вы ?",
                fontSize = 44.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Выберите аккаунт",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        items(
             state.accounts,
            key = {it.id}
        ) { item ->
            AccountItem(
                account = item,
                isSelected = item.isSelected,
                onClick = {
                    actions.onChangeAccount(item)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )
        }
    }
}

@Composable
fun AccountItem(
    account: AccountItem,
    onClick: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
) {

    val textColor = MaterialTheme.colorScheme.onPrimary.takeIf {
        isSelected
    } ?: Color.Black

    Box(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(
                MaterialTheme.colorScheme.primary.takeIf {
                    isSelected
                } ?: MaterialTheme.colorScheme.background,
            )
            .then(modifier)
    ) {
        Image(
            painter = painterResource(account.iconRes),
            contentDescription = null,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 10.dp
                )
                .clip(CircleShape)
                .size(32.dp)
                .align(Alignment.CenterStart)
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ){
            Text(
                text = account.name,
                fontWeight = FontWeight.Medium,
                color = textColor,
            )
            account.role?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.Light,
                    color = textColor.copy(alpha = .7f),
                )
            }
        }
    }
}

@Preview
@Composable
private fun SelectAccountPreview() {
    SelectAccount(
        state = LoginState(
            accounts = listOf(
                AccountItem(
                    name = "Romanova",
                    iconRes = R.drawable.avatar1,
                    isSelected = true,
                    id = 1,
                ),
                AccountItem(
                    name = "Leonid",
                    iconRes = R.drawable.avatar2,
                    isSelected = false,
                    id = 2,
                )
            )
        ),
        actions = object : LoginListener {
            override fun flowNext(uiState: LoginState) {
                TODO("Not yet implemented")
            }

            override fun flowBack(uiState: LoginState) {
                TODO("Not yet implemented")
            }

            override fun onChangeAccount(account: AccountItem) {
                TODO("Not yet implemented")
            }

            override fun onChangeAuthType(type: AuthType) {
                TODO("Not yet implemented")
            }

        }
    )
}