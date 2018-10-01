package com.github.rskupnik.thestory.domain.callback

import com.github.rskupnik.thestory.domain.action.Action
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