package com.example.vknext.core.data

import com.example.vknext.core.data.repositories.forms.FormsFeedbackRepository
import com.example.vknext.core.data.repositories.forms.FormsFeedbackRepositoryImpl
import com.example.vknext.core.data.repositories.users.UserRepository
import com.example.vknext.core.data.repositories.users.UsersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreDataModule {

    @Binds
    abstract fun bindUserRepository(usersRepositoryImpl: UsersRepositoryImpl): UserRepository

    @Binds
    abstract fun bingFormsFeedbackRepository(formsFeedbackRepositoryImpl: FormsFeedbackRepositoryImpl): FormsFeedbackRepository
}