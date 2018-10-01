package com.github.rskupnik.thestory.domain.callback.internal

import com.github.rskupnik.thestory.domain.action.ActionService
import com.github.rskupnik.thestory.domain.callback.Callback
import com.github.rskupnik.thestory.domain.callback.CallbackService
import com.github.rskupnik.thestory.shared.entity.EntityId

internal class CallbackServiceImplementation(
        private val actionService: ActionService
) : CallbackService {
    override fun execute(callback: Callback, entityId: EntityId) {
        callback.actions.forEach { actionService.execute(it, null, entityId, null) }
    }
}