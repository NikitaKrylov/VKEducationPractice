package com.example.vknext.ui.createFeedBack.state

import com.example.vknext.core.data.models.FeedBackGroup
import com.example.vknext.core.data.models.FeedBackPoint


data class CreateFeedBackState(
    val groups: List<FeedBackGroupItem> = emptyList()
)


data class FeedBackGroupItem(
    val hashtag: String,
    val points: List<PointItem> = emptyList(),
)

data class PointItem(
    val id: Long,
    val text: String,
    val isSelected: Boolean = false,
)


fun FeedBackGroup.toItem(): FeedBackGroupItem {
    return FeedBackGroupItem(
        hashtag = hashtag,
        points = points.map { it.toItem() },
    )
}

fun FeedBackPoint.toItem() : PointItem {
    return PointItem(
        id = id,
        text = name,
    )
}