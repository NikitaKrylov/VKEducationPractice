package com.example.vknext.ui.feedback.state

import com.example.vknext.ui.login.state.AccountItem


sealed class FeedBackState {
    data object Loading : FeedBackState()

    data object Error : FeedBackState()

    data class ShowFeedBack(
        val search: String = "",
        val searchItems: List<AccountItem> = emptyList(),
        val isLoading:  Boolean = false,
    ) : FeedBackState()
}