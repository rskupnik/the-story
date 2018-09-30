package com.github.rskupnik.thestory.domain.action

import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.entity.EntityId

interface ActionService {
    fun dispatch(action: Action, context: Context, entityId: EntityId, data: Map<String, Any>)
}