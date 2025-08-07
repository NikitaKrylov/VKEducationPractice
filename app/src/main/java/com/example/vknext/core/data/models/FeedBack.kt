package com.example.vknext.core.data.models

data class FeedBackForm(
    val groups: List<FeedBackGroup>,
    val type: FeedBackType
)

enum class FeedBackType {
    THANKS,
    WISHES,
}

data class FeedBackGroup(
    val hashtag: String,
    val isDefault: Boolean = true,
    val points: List<FeedBackPoint>
)

data class FeedBackPoint(
    val id: Long,
    val name: String
)