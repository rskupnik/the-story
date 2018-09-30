package com.github.rskupnik.thestory.domain.option

import com.github.rskupnik.thestory.domain.action.ActionService
import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.entity.EntityId

internal class OptionServiceImplementation(
        private val actionService: ActionService
) : OptionService {

    override fun execute(option: Option, externalData: Map<String, Any>, context: Context, entityId: EntityId) {
        option.actions.forEach {
            actionService.execute(it, context, entityId, externalData)
        }
    }
}