package com.example.vknext.core.data.repositories.knowladge

import com.example.vknext.core.data.models.Knowladge

interface KnowladgeRepository {
    fun getById(itemId: Int): Knowladge?
    fun getAll(): List<Knowladge>
}