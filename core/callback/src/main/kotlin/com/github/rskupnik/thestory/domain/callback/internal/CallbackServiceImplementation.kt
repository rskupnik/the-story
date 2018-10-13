package com.github.rskupnik.thestory.domain.callback.internal

import com.github.rskupnik.thestory.core.callback.domain.Callback
import com.github.rskupnik.thestory.core.callback.event.CallbackTriggeredEvent
import com.github.rskupnik.thestory.domain.callback.CallbackService
import com.github.rskupnik.thestory.event.EventDispatcher
import com.github.rskupnik.thestory.shared.entity.EntityId

internal class CallbackServiceImplementation(
        private val eventDispatcher: EventDispatcher
) : CallbackService {
    override fun execute(callback: Callback, entityId: EntityId) {
        eventDispatcher.dispatch(CallbackTriggeredEvent(callback, entityId))
    }
}