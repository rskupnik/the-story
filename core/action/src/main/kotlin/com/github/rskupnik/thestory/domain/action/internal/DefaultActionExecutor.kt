package com.github.rskupnik.thestory.domain.action.internal

import com.github.rskupnik.thestory.action.domain.Action
import com.github.rskupnik.thestory.core.callback.event.CallbackTriggeredEvent
import com.github.rskupnik.thestory.domain.`object`.ObjectService
import com.github.rskupnik.thestory.domain.action.ActionExecutor
import com.github.rskupnik.thestory.domain.action.ActionHandler
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.domain.option.event.OptionSelectedEvent
import com.github.rskupnik.thestory.event.EventDispatcher
import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.ExternalState
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.entity.EntityId
import com.github.rskupnik.thestory.shared.entity.EntityType

internal class DefaultActionExecutor(
        private val itemService: ItemService,
        private val objectService: ObjectService,
        eventDispatcher: EventDispatcher
) : ActionExecutor {

    private val handlers: MutableMap<String, ActionHandler> = HashMap()

    init {
        eventDispatcher.register(OptionSelectedEvent::class) { event ->
            event as OptionSelectedEvent
            event.option.actions.forEach { action ->
                execute(action, event.context, event.entityId, event.externalData)
            }
        }

        eventDispatcher.register(CallbackTriggeredEvent::class) { event ->
            event as CallbackTriggeredEvent
            event.callback.actions.forEach { action ->
                execute(action, null, event.entityId, null)
            }
        }
    }

    override fun register(id: String, handler: ActionHandler) {
        handlers[id] = handler
    }

    private fun execute(action: Action, context: Context?, entityId: EntityId?, data: Map<String, Any>?) {
        if (contextValid(action, context) && conditionsMet(action, entityId)) {
            handlers[action.id]?.handle(action, entityId, data)
        }
    }

    private fun contextValid(action: Action, context: Context?): Boolean =
            if (action.contexts == null || action.contexts!!.isEmpty() || context == null) true
            else action.contexts!!.firstOrNull { context == Context.fromString(it) } != null

    private fun conditionsMet(action: Action, entityId: EntityId?): Boolean {
        if (entityId == null || action.conditions == null ||action.conditions!!.isEmpty()) return true
        val externalState = fetchExternalState(entityId) ?: return false

        return action.conditions!!.entries.none { it.value != externalState[it.key] }
    }

    private fun fetchExternalState(entityId: EntityId): ExternalState? =
            when (entityId.type) {
                EntityType.ITEM -> itemService.getItemView(Reference.to(entityId.id))?.externalState
                EntityType.OBJECT -> objectService.getObjectView(Reference.to(entityId.id))?.externalState
                else -> null
            }
}