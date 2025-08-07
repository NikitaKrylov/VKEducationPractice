package com.example.vknext.ui.login

import androidx.lifecycle.ViewModel
import com.example.vknext.core.data.repositories.users.UserRepository
import com.example.vknext.domain.LogInUseCase
import com.example.vknext.ui.login.state.AccountItem
import com.example.vknext.ui.login.state.AuthType
import com.example.vknext.ui.login.state.LoginSideEffect
import com.example.vknext.ui.login.state.LoginState
import com.example.vknext.ui.login.state.LoginStep
import com.example.vknext.ui.login.state.toItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository,
    private val logInUseCase: LogInUseCase,
) : ViewModel() {
    private val mutableState = MutableStateFlow(LoginState())

    private val mutableSideEffect = Channel<LoginSideEffect>()

    val sideEffect = mutableSideEffect.receiveAsFlow()

    val state = mutableState.asStateFlow()

    init {
        mutableState.update {
            LoginState(
                accounts = repository.getAll().map { it.toItem() }
            )
        }
    }

    fun updateAuthType(authType: AuthType) {
        mutableState.update  {
            it.copy(
                authType = authType
            )
        }
    }

    fun updateCurrentAccount(account: AccountItem) {
        mutableState.update { currentState ->
            currentState.copy(
                accounts = currentState.accounts.map {
                    it.copy(
                        isSelected = it.id == account.id
                    )
                }
            )
        }
    }

    fun flowNext(uiState: LoginState) {
        when (uiState.step) {
            LoginStep.AUTH_TYPE -> {
                mutableState.update { currentState ->
                    currentState.copy(
                        step = LoginStep.SELECT_ACCOUNT.takeIf {
                            currentState.isAuthTypeSelected()
                        } ?: currentState.step
                    )
                }
            }
            LoginStep.SELECT_ACCOUNT -> {
                if (uiState.isAccountSelected()) {
                    logIn(uiState)
                }
            }
        }
    }

    fun flowBack(uiState: LoginState) {
        when (uiState.step) {
            LoginStep.AUTH_TYPE -> {}
            LoginStep.SELECT_ACCOUNT -> {
                mutableState.update { currentState ->
                    currentState.copy(
                        step = LoginStep.AUTH_TYPE
                    )
                }
            }
        }
    }

    private fun logIn(
        uiState: LoginState
    ) {
        val userId = uiState.accounts.find { it.isSelected }?.id ?: return

        logInUseCase(userId)

        mutableSideEffect.trySend(LoginSideEffect.SuccessLogin)
    }

    private fun LoginState.isAccountSelected() : Boolean {
        return this.accounts.any { it.isSelected }
    }

    private fun LoginState.isAuthTypeSelected() : Boolean {
        return this.authType != null
    }
}