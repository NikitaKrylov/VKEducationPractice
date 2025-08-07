package com.example.vknext.core.data.repositories.forms

import com.example.vknext.core.data.models.FeedBackForm
import com.example.vknext.core.data.models.FeedBackType

interface FormsFeedbackRepository {
    fun getAll(): List<FeedBackForm>
    fun getByType(type: FeedBackType): FeedBackForm?
}