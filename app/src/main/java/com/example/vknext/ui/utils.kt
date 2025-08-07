package com.example.vknext.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.updateAndGet

inline fun <reified Parent, reified Child> MutableStateFlow<Parent>.updateIfItMatches(block: (Child) -> Parent): Parent {
    return this.updateAndGet {
        (it as? Child)?.let(block) ?: it
    }
}