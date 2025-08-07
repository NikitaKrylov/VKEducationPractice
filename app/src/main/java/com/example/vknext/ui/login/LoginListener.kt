package com.example.vknext.ui.login

import com.example.vknext.ui.login.state.AccountItem
import com.example.vknext.ui.login.state.AuthType
import com.example.vknext.ui.login.state.LoginState

interface LoginListener {
    fun flowNext(uiState: LoginState)
    fun flowBack(uiState: LoginState)
    fun onChangeAccount(account: AccountItem)
    fun onChangeAuthType(type: AuthType)
}