package com.github.rskupnik.thestory.domain.action

abstract class AbstractActionHandler(
        executor: ActionExecutor
) : ActionHandler {
    init {
        executor.register(handledId, this)
    }
}