package com.example.vknext.ui.createFeedBack

interface CreateFeedbackListener {
    fun updatePointItem(pointId: Long, isSelected: Boolean)
    fun saveForm()
}