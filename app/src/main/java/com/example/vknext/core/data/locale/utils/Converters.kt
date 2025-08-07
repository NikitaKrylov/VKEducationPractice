package com.example.vknext.core.data.locale.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.vknext.core.data.models.FeedBackType


@ProvidedTypeConverter
class Converters {
    @TypeConverter fun toFeedbackType(value: String) = enumValueOf<FeedBackType>(value)
    @TypeConverter
    fun fromFeedbackType(type: FeedBackType) = type.name
}