package com.example.vknext.core.common.secure

import com.example.vknext.core.common.secure.models.AuthContext
import com.example.vknext.core.common.storages.EncryptedStorage
import com.example.vknext.core.common.storages.LocaleStorage
import com.example.vknext.core.common.storages.StorageKeys
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class AuthContextProviderImpl @Inject constructor(
    @EncryptedStorage private val localeStorage: LocaleStorage
): AuthContextProvider {
    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }


    override fun getAuthContext(): AuthContext? {
        return localeStorage.getString(StorageKeys.AUTH_CONTEXT, null)?.let {
            json.decodeFromString<AuthContext>(it)
        }
    }

    override fun updateAuthContext(context: AuthContext) {
        json.encodeToString<AuthContext>(context).let {
            localeStorage.put(StorageKeys.AUTH_CONTEXT, it)
        }
    }

    override fun clear() {
        localeStorage.remove(StorageKeys.AUTH_CONTEXT)
    }
}

