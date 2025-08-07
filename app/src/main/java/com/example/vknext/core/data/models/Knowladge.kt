package com.example.vknext.core.data.models

data class Knowladge(
    val id: Int,
    val title: String,
    val description: String? = null,
    val text: String,
    val relatedTo: Int? = null,
)