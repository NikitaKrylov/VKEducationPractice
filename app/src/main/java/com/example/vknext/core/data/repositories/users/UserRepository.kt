package com.example.vknext.core.data.repositories.users

import com.example.vknext.core.data.models.Account

interface UserRepository {
    fun getById(accountId: Long) : Account?
    fun getAll(): List<Account>
}