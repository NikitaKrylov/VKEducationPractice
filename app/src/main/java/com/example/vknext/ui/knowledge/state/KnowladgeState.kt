package com.example.vknext.ui.knowledge.state

import com.example.vknext.core.data.models.Knowladge

data class KnowladgeState(
    val items: List<KnowladgeItem> = emptyList()
)


data class KnowladgeItem(
    val id: Int,
    val title: String,
)

fun Knowladge.toItem() : KnowladgeItem {
    return KnowladgeItem(
        id = this.id,
        title = this.title
    )
}