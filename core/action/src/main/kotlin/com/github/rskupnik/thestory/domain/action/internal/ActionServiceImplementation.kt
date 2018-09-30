package com.github.rskupnik.thestory.domain.action.internal

import com.github.rskupnik.thestory.domain.action.Action
import com.github.rskupnik.thestory.domain.action.ActionService
import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.entity.EntityId

internal class ActionServiceImplementation(

) : ActionService, ActionExecutor {

    override fun register(id: String, handler: ActionHandler) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dispatch(action: Action, context: Context, entityId: EntityId, data: Map<String, Any>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}