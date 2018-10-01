package com.github.rskupnik.thestory.domain.callback

import com.github.rskupnik.thestory.shared.entity.EntityId

interface CallbackService {
    fun execute(callback: Callback, entityId: EntityId)
}