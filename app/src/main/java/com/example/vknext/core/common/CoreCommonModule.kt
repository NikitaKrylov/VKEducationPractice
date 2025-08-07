package com.example.vknext.core.common

import android.content.Context
import com.example.vknext.core.common.secure.AuthContextProvider
import com.example.vknext.core.common.secure.AuthContextProviderImpl
import com.example.vknext.core.common.storages.EncryptedStorage
import com.example.vknext.core.common.storages.EncryptedStorageImpl
import com.example.vknext.core.common.storages.LocaleStorage
import com.example.vknext.core.common.storages.SharedPreferencesStorageImpl
import com.example.vknext.core.common.storages.SharedStorage
import com.example.vknext.core.common.storages.Storages
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoreCommonModule {

    @EncryptedStorage
    @Provides
    @Singleton
    fun provideEncryptedStorage(
        @ApplicationContext context: Context
    ) : LocaleStorage {
        return EncryptedStorageImpl(
            context = context,
            gson = GsonBuilder().create(),
            storageName = Storages.ENCRYPTED,
        )
    }

    @SharedStorage
    @Provides
    @Singleton
    fun provideSharedStorage(
        @ApplicationContext context: Context
    ) : LocaleStorage {
        return SharedPreferencesStorageImpl(
            context = context,
            gson = GsonBuilder().create(),
            storageName = Storages.SHARED
        )
    }

    @Provides
    @Singleton
    fun provideAuthContextProvider(
        @EncryptedStorage localeStorage: LocaleStorage,
    ): AuthContextProvider = AuthContextProviderImpl(
        localeStorage = localeStorage,
    )
}
