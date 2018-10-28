package com.github.rskupnik.thestory.domain.action

import com.github.rskupnik.thestory.action.domain.Action
import com.github.rskupnik.thestory.shared.entity.EntityId

interface ActionHandler {
    fun id(): String

    fun handle(action: Action, entityId: EntityId?, data: Map<String, Any>?)
}