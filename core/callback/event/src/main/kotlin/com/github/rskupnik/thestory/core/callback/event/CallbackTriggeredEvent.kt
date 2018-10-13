package com.github.rskupnik.thestory.core.callback.event

import com.github.rskupnik.thestory.core.callback.domain.Callback
import com.github.rskupnik.thestory.event.Event
import com.github.rskupnik.thestory.shared.entity.EntityId

data class CallbackTriggeredEvent(
        val callback: Callback,
        val entityId: EntityId
) : Event {
}