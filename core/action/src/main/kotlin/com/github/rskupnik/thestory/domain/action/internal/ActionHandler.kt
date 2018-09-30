package com.github.rskupnik.thestory.domain.action.internal

import com.github.rskupnik.thestory.domain.action.Action
import com.github.rskupnik.thestory.shared.entity.EntityId

internal interface ActionHandler {
    val handledId: String

    fun handle(action: Action, entityId: EntityId?, data: Map<String, Any>?)
}