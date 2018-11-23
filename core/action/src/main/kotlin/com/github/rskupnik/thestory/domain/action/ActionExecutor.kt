package com.github.rskupnik.thestory.domain.action

/**
 * Executes actions by delegating to [ActionHandler]s registered for the specific id
 */
interface ActionExecutor {

    /**
     * Register an [ActionHandler] for the [id].
     *
     * The [handler] will be executed for the action identified by [id].
     */
    fun register(id: String, handler: ActionHandler)
}