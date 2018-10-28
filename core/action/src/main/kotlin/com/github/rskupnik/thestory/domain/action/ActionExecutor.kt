package com.github.rskupnik.thestory.domain.action

interface ActionExecutor {
    fun register(id: String, handler: ActionHandler)
}