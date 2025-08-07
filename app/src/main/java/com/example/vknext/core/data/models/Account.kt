package com.example.vknext.core.data.models

data class Account(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val iconRes: Int,
    val role: String,
    val isAdmin: Boolean,
)
