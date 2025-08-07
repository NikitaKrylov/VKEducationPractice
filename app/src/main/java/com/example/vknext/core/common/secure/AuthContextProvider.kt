package com.example.vknext.core.common.secure

import com.example.vknext.core.common.secure.models.AuthContext

interface AuthContextProvider {
    fun getAuthContext(): AuthContext?
    fun updateAuthContext(context: AuthContext)
    fun clear()
}

fun AuthContextProvider.getAndUpdate(block: AuthContext.() -> Unit)  {
    this.getAuthContext()?.let { context ->
        this.updateAuthContext(
            context.apply { block() }
        )
    }
}