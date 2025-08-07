package com.example.vknext.core.data.locale.dao

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import com.example.vknext.core.data.locale.entity.AnswerEntity
import com.example.vknext.core.data.locale.entity.AnswerPointEntity

@Dao
interface AnswerDao {
    // Создание
    @Insert
    suspend fun insertAnswer(answer: AnswerEntity): Long

    @Insert
    suspend fun insertPoints(points: List<AnswerPointEntity>)

    // Удаление
    @Query("DELETE FROM answers")
    suspend fun deleteAll()

    // Чтение
    @Transaction
    @Query("SELECT * FROM answers")
    suspend fun getAllAnswers(): List<AnswerWithPoints>

    @Transaction
    @Query("SELECT * FROM answers WHERE recipientId = :accountId")
    suspend fun getAnswersTo(accountId: Long): List<AnswerWithPoints>

    @Transaction
    @Query("SELECT * FROM answers WHERE senderId = :accountId")
    suspend fun getAnswersFrom(accountId: Long): List<AnswerWithPoints>
}

// Обёртка для связи ответа с пунктами
data class AnswerWithPoints(
    @Embedded val answer: AnswerEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "answerId"
    )
    val points: List<AnswerPointEntity>
)