package com.github.rskupnik.thestory.domain.action

import com.github.rskupnik.thestory.action.domain.Action
import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.entity.EntityId

interface ActionService {
    fun execute(action: Action, context: Context?, entityId: EntityId?, data: Map<String, Any>?)
}