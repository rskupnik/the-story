package com.github.rskupnik.thestory.domain.callback

import com.github.rskupnik.thestory.domain.action.Action

data class Callback(
        val id: CallbackId,
        val actions: List<Action>
) {
    companion object {
        fun find(id: CallbackId, callbacks: List<Callback>): Callback? =
                callbacks.find { it.id == id }
    }
}