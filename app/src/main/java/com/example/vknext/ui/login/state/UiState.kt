package com.example.vknext.ui.login.state

import com.example.vknext.R
import com.example.vknext.core.data.models.Account

data class LoginState(
    val accounts: List<AccountItem> = emptyList(),
    val authType: AuthType? = null,
    val step: LoginStep = LoginStep.AUTH_TYPE,
) {
    val currentUser: AccountItem?
        get() = accounts.find { it.isSelected }
}

enum class LoginStep(
    val buttonActionRes: Int,
) {
    AUTH_TYPE(
        buttonActionRes = R.string.next
    ),
    SELECT_ACCOUNT(
        buttonActionRes = R.string.log_in
    ),
}

enum class AuthType(
    val titleRes: Int,
) {
    ADMIN(
        titleRes = R.string.auth_type_admin,
    ),
    WORKER(
        titleRes = R.string.auth_type_worker
    ),
}


data class AccountItem(
    val id: Long,
    val isSelected: Boolean = false,
    val name: String,
    val iconRes: Int,
    val role: String? = null,
)

fun Account.toItem() : AccountItem {
    return AccountItem(
        id = id,
        name = "$lastName $firstName",
        isSelected = false,
        iconRes = iconRes,
        role = role,
    )
}