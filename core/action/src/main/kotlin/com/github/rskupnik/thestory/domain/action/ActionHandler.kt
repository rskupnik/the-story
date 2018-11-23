package com.github.rskupnik.thestory.domain.action

import com.github.rskupnik.thestory.action.domain.Action
import com.github.rskupnik.thestory.shared.entity.EntityId

/**
 * A handler for a specific action.
 */
interface ActionHandler {

    /**
     * [String] that identifies the action to be handled.
     */
    fun id(): String

    /**
     * Handle the [action] launched for the specific entity ([entityId]) with external [data] provided.
     */
    fun handle(action: Action, entityId: EntityId?, data: Map<String, Any>?)
}