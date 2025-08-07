package com.example.vknext.core.data.repositories.users

import com.example.vknext.R
import com.example.vknext.core.data.models.Account
import javax.inject.Inject
import javax.inject.Singleton


class UsersRepositoryImpl @Inject constructor(): UserRepository  {
    override fun getById(accountId: Long): Account? {
        return accounts.find { it.id == accountId }
    }

    override fun getAll(): List<Account> {
        return accounts
    }

    companion object {
        private val accounts = listOf(
            Account(
                firstName = "Настя",
                lastName = "Романова",
                iconRes = R.drawable.avatar1,
                isAdmin = false,
                id = 1,
                role = "Design Trainee",
            ),
            Account(
                firstName = "Леонид",
                lastName = "Тимохин",
                iconRes = R.drawable.avatar2,
                isAdmin = false,
                id = 2,
                role = "ML Engineer",
            ),
            Account(
                firstName = "Станисав",
                lastName = "Кулашин",
                iconRes = R.drawable.avatar3,
                isAdmin = false,
                id = 3,
                role = "Junior Python Backend",
            ),
            Account(
                firstName = "Ярослав",
                lastName = "Сокин",
                iconRes = R.drawable.avatar4,
                isAdmin = false,
                id = 4,
                role = "Frontent Lead",
            ),
            Account(
                firstName = "Андрей",
                lastName = "Кирилин",
                iconRes = R.drawable.avatar5,
                isAdmin = false,
                id = 5,
                role = "Middle System Analytic",
            ),
            Account(
                firstName = "Дмитрий",
                lastName = "Григорян",
                iconRes = R.drawable.avatar6,
                isAdmin = false,
                id = 6,
                role = "Kebab Owner",
            ),
            Account(
                firstName = "Тимофей",
                lastName = "Костров",
                iconRes = R.drawable.avatar7,
                isAdmin = false,
                id = 7,
                role = "Senior Ui/Ux Designer",
            ),
        )
    }
}