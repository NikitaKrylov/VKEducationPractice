package com.example.vknext.ui.login.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vknext.ui.login.LoginListener
import com.example.vknext.ui.login.state.AccountItem
import com.example.vknext.ui.login.state.AuthType
import com.example.vknext.ui.login.state.LoginState
import com.example.vknext.ui.login.state.LoginStep
import com.example.vknext.ui.uikit.PrimaryButton

@Composable
fun Login(
    state: LoginState,
    actions: LoginListener
) {
    Box(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxSize()
    ) {
        when (state.step) {
            LoginStep.AUTH_TYPE -> SelectAuthType(
                state = state,
                actions = actions,
                modifier = Modifier
                    .align(Alignment.Center)
            )
            LoginStep.SELECT_ACCOUNT -> SelectAccount(
                state = state,
                actions = actions,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        PrimaryButton(
            text = stringResource(state.step.buttonActionRes),
            onClick = {
                actions.flowNext(state)
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}

@Preview
@Composable
private fun LoginPreview() {
    Login(
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