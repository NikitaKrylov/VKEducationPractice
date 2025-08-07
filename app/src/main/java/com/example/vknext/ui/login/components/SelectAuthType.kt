package com.example.vknext.ui.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vknext.ui.login.LoginListener
import com.example.vknext.ui.login.state.AccountItem
import com.example.vknext.ui.login.state.AuthType
import com.example.vknext.ui.login.state.LoginState

@Composable
fun SelectAuthType(
    state: LoginState,
    actions: LoginListener,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier
            .then(modifier)
    ) {
        Text(
            text = "Добрый день!",
            fontSize = 48.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "Давайте познакомимся, кто вы?"
        )

        AuthType.entries.forEach { type ->
            AuthType(
                authType = type,
                isSelected = state.authType == type,
                onSelect = {
                    actions.onChangeAuthType(type)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )
        }
    }
}

@Composable
fun AuthType(
    authType: AuthType,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .clickable {
                onSelect()
            }
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(
                MaterialTheme.colorScheme.primary.takeIf {
                    isSelected
                } ?: Color.Transparent
            )
            .then(modifier)
    ) {
        Text(
            text = stringResource(authType.titleRes),
            color = MaterialTheme.colorScheme.onPrimary.takeIf { isSelected } ?: Color.Black,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun SelectAuthTypePreview() {
    SelectAuthType(
        state = LoginState(),
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