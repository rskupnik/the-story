package com.github.rskupnik.thestory.domain.action.internal

internal abstract class AbstractActionHandler(
        executor: ActionExecutor
) : ActionHandler {
    init {
        executor.register(handledId, this)
    }
}