package com.github.rskupnik.thestory.core.callback.domain

import com.github.rskupnik.thestory.action.domain.Action
import com.github.rskupnik.thestory.shared.Definition

data class Callback(
        val id: CallbackId,
        val actions: List<Action>
) : Definition {
    companion object {
        fun find(id: CallbackId, callbacks: List<Callback>): Callback? =
                callbacks.find { it.id == id }
    }
}