package com.example.vknext.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.vknext.ui.ProfileRoute
import com.example.vknext.ui.login.components.Login
import com.example.vknext.ui.login.state.AccountItem
import com.example.vknext.ui.login.state.AuthType
import com.example.vknext.ui.login.state.LoginSideEffect
import com.example.vknext.ui.login.state.LoginState
import kotlinx.coroutines.flow.filterNotNull

@Composable
fun LoginScreen(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val sideEffect = viewModel.sideEffect.collectAsStateWithLifecycle(null)

    val actions = object : LoginListener {
        override fun flowNext(uiState: LoginState) {
            viewModel.flowNext(uiState)
        }

        override fun flowBack(uiState: LoginState) {
            viewModel.flowBack(uiState)
        }

        override fun onChangeAccount(account: AccountItem) {
            viewModel.updateCurrentAccount(account)
        }

        override fun onChangeAuthType(type: AuthType) {
            viewModel.updateAuthType(type)
        }
    }


    Login(
        state = state,
        actions = actions,
    )

    LaunchedEffect(Unit) {
        snapshotFlow { sideEffect.value }.filterNotNull().collect { sideEffect ->
            when (sideEffect) {
                LoginSideEffect.SuccessLogin -> {
                    navController.navigate(ProfileRoute.toRoute())
                }
            }
        }
    }
}