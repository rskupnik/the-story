package com.github.rskupnik.thestory.domain.action

// TODO: Should this be moved to :core:action:handlers?
abstract class AbstractActionHandler(
        executor: ActionExecutor
) : ActionHandler {
    init {
        executor.register(id(), this)
    }
}