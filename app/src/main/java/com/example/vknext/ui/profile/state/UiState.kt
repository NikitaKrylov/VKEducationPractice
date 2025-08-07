package com.example.vknext.ui.profile.state

import com.example.vknext.core.data.models.Answer
import com.example.vknext.core.data.models.FeedBackPoint
import com.example.vknext.domain.AnswerWithSender


sealed class ProfileState {
    data object Loading : ProfileState()

    data object Error : ProfileState()

    data class ShowProfile(
        val username: String,
        val role: String,
        val avatarId: Int? = null,
        val thanks: List<AnswerWithSender> = emptyList(),
        val totalThanks: Int = 0,
        val todayThanks: Int = 0,
        val wishes: List<AnswerWithSender> = emptyList(),
        val totalWishes: Int = 0,
        val todayWishes: Int = 0,
    ) : ProfileState()
}