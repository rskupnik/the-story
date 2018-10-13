package com.github.rskupnik.thestory.domain.callback.internal

import com.github.rskupnik.thestory.core.callback.domain.Callback
import com.github.rskupnik.thestory.shared.entity.EntityId

interface CallbackHandler {
    fun handle(callback: Callback, entityId: EntityId)
}