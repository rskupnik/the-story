package com.github.rskupnik.thestory.domain.action.internal

import com.github.rskupnik.thestory.domain.action.Action
import com.github.rskupnik.thestory.domain.action.ActionService
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.ExternalState
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.entity.EntityId
import com.github.rskupnik.thestory.shared.entity.EntityType

internal class ActionServiceImplementation(
        private val itemService: ItemService
        // TODO: add objectService
) : ActionService, ActionExecutor {

    private val handlers: MutableMap<String, ActionHandler> = HashMap()

    override fun register(id: String, handler: ActionHandler) {
        handlers[id] = handler
    }

    override fun execute(action: Action, context: Context?, entityId: EntityId?, data: Map<String, Any>?) {
        if (contextValid(action, context) && conditionsMet(action, entityId)) {
            handlers[action.id]?.handle(action, entityId, data)
        }
    }

    private fun contextValid(action: Action, context: Context?): Boolean =
        if (action.contexts.isEmpty() || context == null) true
        else action.contexts.firstOrNull { context == Context.fromString(it) } != null

    private fun conditionsMet(action: Action, entityId: EntityId?): Boolean {
        if (entityId == null || action.conditions.isEmpty()) return true
        val externalState = fetchExternalState(entityId) ?: return false

        return action.conditions.entries.none { it.value != externalState[it.key] }
    }

    private fun fetchExternalState(entityId: EntityId): ExternalState? =
            when (entityId.type) {
                EntityType.ITEM -> itemService.getItemView(Reference.to(entityId.id))?.externalState
                EntityType.OBJECT -> null // TODO: Handle Object here
                else -> null
            }
}