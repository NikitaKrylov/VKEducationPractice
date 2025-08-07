package com.example.vknext.ui.login.state

sealed interface LoginSideEffect {
    data object SuccessLogin : LoginSideEffect
}