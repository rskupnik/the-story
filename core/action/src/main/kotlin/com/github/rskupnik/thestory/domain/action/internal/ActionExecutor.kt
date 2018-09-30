package com.github.rskupnik.thestory.domain.action.internal

internal interface ActionExecutor {
    fun register(id: String, handler: ActionHandler)
}