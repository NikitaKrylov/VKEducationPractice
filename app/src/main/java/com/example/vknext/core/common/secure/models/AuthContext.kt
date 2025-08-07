package com.example.vknext.core.common.secure.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthContext(
    val userId: Long,
//    val accessToken: String,
//    val refreshToken: String? = null,
    val updatedAt: Long? = null,
    val userData: UserData? = null,
)

@Serializable
data class UserData(
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val avatarId: Int? = null,
    val role: String? = null,
)
