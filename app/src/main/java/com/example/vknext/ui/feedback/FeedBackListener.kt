package com.example.vknext.ui.feedback

interface FeedBackListener {
    fun onItemClick(accountId: Long)
    fun onSearchTextChanged(value: String)
}