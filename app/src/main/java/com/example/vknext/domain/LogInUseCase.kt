package com.example.vknext.domain

import com.example.vknext.core.common.secure.AuthContextProvider
import com.example.vknext.core.common.secure.models.AuthContext
import com.example.vknext.core.common.secure.models.UserData
import com.example.vknext.core.data.repositories.users.UserRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LogInUseCase @Inject constructor(
    private val authProvider: AuthContextProvider,
    private val repository: UserRepository,
) {
    operator fun invoke(
        userId: Long,
    ) {
        val user = repository.getById(userId) ?: throw Exception()
        authProvider.updateAuthContext(
            context = AuthContext(
                userId = user.id,
                userData = UserData(
                    firstName = user.firstName,
                    lastName = user.lastName,
                    avatarId = user.iconRes,
                    role = user.role,
                )
            )
        )
    }
}