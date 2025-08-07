package com.example.vknext.core.data.repositories.answers

import com.example.vknext.core.data.locale.dao.AnswerDao
import com.example.vknext.core.data.locale.dao.AnswerWithPoints
import com.example.vknext.core.data.locale.entity.AnswerEntity
import com.example.vknext.core.data.locale.entity.AnswerPointEntity
import com.example.vknext.core.data.models.Answer
import com.example.vknext.core.data.models.FeedBackPoint
import com.example.vknext.core.data.models.FeedBackType
import com.example.vknext.core.data.models.toAnswer
import java.time.Instant
import javax.inject.Inject


class AnswersRepositoryImpl @Inject constructor(
    private val dao: AnswerDao
) : AnswersRepository {

    override suspend fun createAnswer(
        senderId: Long,
        recipientId: Long,
        feedbackType: FeedBackType,
        selectedPoints: List<FeedBackPoint>,
        groupHashtag: String
    ) {
        // Сохраняем исторические данные
        val answerId = dao.insertAnswer(
            AnswerEntity(
                feedbackType = feedbackType,
                senderId = senderId,
                recipientId = recipientId,
            )
        )

        dao.insertPoints(
            selectedPoints.map { point ->
                AnswerPointEntity(
                    answerId = answerId,
                    pointId = point.id,
                    pointName = point.name,
                    groupHashtag = groupHashtag
                )
            }
        )
    }

    override suspend fun deleteAllAnswers() = dao.deleteAll()

    override suspend fun getAllAnswers(): List<Answer> =
        dao.getAllAnswers().map { it.toAnswer() }

    override suspend fun getAnswersTo(accountId: Long): List<Answer> {
        return dao.getAnswersTo(accountId).map { it.toAnswer() }
    }

    override suspend fun getAnswersFrom(accountId: Long): List<Answer> {
        return dao.getAnswersFrom(accountId).map { it.toAnswer() }
    }

}