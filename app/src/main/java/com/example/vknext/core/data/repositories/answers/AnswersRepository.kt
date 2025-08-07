package com.example.vknext.core.data.repositories.answers

import com.example.vknext.core.data.models.Account
import com.example.vknext.core.data.models.Answer
import com.example.vknext.core.data.models.FeedBackPoint
import com.example.vknext.core.data.models.FeedBackType

interface AnswersRepository {
    suspend fun createAnswer(
        senderId: Long,
        recipientId: Long,
        feedbackType: FeedBackType,
        selectedPoints: List<FeedBackPoint>,
        groupHashtag: String // для каждого пункта
    )

    suspend fun deleteAllAnswers()
    suspend fun getAllAnswers(): List<Answer>
    suspend fun getAnswersTo(accountId: Long): List<Answer>
    suspend fun getAnswersFrom(accountId: Long): List<Answer>
}