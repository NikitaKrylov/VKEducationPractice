package com.example.vknext.core.data.locale.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.example.vknext.core.data.models.FeedBackType

// Таблица для хранения ответов
@Entity(tableName = "answers")
data class AnswerEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val createdAt: Long = System.currentTimeMillis(), // временная метка
    val feedbackType: FeedBackType, // enum: THANKS/WISHES
    // Данные отправителя (исторические)
    val senderId: Long,
    val recipientId: Long,
)

// Таблица для выбранных пунктов (отношение 1:м к AnswerEntity)
@Entity(
    tableName = "answer_points",
    foreignKeys = [ForeignKey(
        entity = AnswerEntity::class,
        parentColumns = ["id"],
        childColumns = ["answerId"],
        onDelete = CASCADE
    )]
)
data class AnswerPointEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val answerId: Long, // связь с ответом
    val pointId: Long, // ID пункта
    val pointName: String, // название на момент ответа
    val groupHashtag: String // хештег группы
)