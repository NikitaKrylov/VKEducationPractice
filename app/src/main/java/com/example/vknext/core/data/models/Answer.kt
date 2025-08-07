package com.example.vknext.core.data.models

import com.example.vknext.core.data.locale.dao.AnswerWithPoints

data class Answer(
    val id: Long,
    val type: FeedBackType,
    val recipientId: Long,
    val senderId: Long,
    val points: List<FeedBackPoint>
)


fun AnswerWithPoints.toAnswer() : Answer {
    return Answer(
        id = answer.id,
        type = answer.feedbackType,
        recipientId = answer.recipientId,
        senderId = answer.senderId,
        points = points.map {
            FeedBackPoint(
                id = it.id,
                name = it.pointName,
            )
        }
    )
}
