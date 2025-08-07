package com.example.vknext.domain

import com.example.vknext.core.data.models.Account
import com.example.vknext.core.data.models.FeedBackPoint
import com.example.vknext.core.data.models.FeedBackType
import com.example.vknext.core.data.repositories.answers.AnswersRepository
import com.example.vknext.core.data.repositories.users.UserRepository
import javax.inject.Inject

class GetFeedBackWithSender @Inject constructor(
    private val usersRepository: UserRepository,
    private val answersRepository: AnswersRepository,
) {
    suspend operator fun invoke(accountId: Long) : List<AnswerWithSender> {
        val answers = answersRepository.getAnswersTo(
            accountId = accountId
        )
        val senders = answers.associate {
            it.senderId to usersRepository.getById(it.senderId)
        }

        return answers.map { answer ->
            AnswerWithSender(
                id = answer.id,
                type = answer.type,
                recipientId = answer.recipientId,
                sender = senders[answer.senderId],
                points = answer.points,
            )
        }
    }
}

data class AnswerWithSender(
    val id: Long,
    val type: FeedBackType,
    val recipientId: Long,
    val sender: Account?,
    val points: List<FeedBackPoint>
)