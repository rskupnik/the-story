package com.github.rskupnik.thestory.domain.callback

import com.github.rskupnik.thestory.core.callback.domain.Callback
import com.github.rskupnik.thestory.shared.entity.EntityId

/**
 * Contains logic to handle [Callback]s.
 *
 * A callback is basically a list of actions that execute at specific events, such as removing an item from equipment.
 */
interface CallbackService {

    /**
     * Execute a [callback] for a specific entity identified by [entityId].
     */
    fun execute(callback: Callback, entityId: EntityId)
}