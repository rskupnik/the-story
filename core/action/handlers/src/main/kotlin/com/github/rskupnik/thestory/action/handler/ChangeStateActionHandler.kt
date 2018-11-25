package com.github.rskupnik.thestory.action.handler

import com.github.rskupnik.thestory.action.domain.Action
import com.github.rskupnik.thestory.domain.`object`.ObjectMutator
import com.github.rskupnik.thestory.domain.`object`.ObjectService
import com.github.rskupnik.thestory.domain.action.AbstractActionHandler
import com.github.rskupnik.thestory.domain.action.ActionExecutor
import com.github.rskupnik.thestory.domain.item.ItemMutator
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.entity.EntityId
import com.github.rskupnik.thestory.shared.entity.EntityType

/**
 * Handles a change in external state.
 *
 * For example, when a torch is lit, this action will set the "toggled" key to "true" or "false".
 */
class ChangeStateActionHandler(
        actionExecutor: ActionExecutor,
        private val itemService: ItemService,
        private val objectService: ObjectService
): AbstractActionHandler(actionExecutor) {
    override fun id(): String = "changeState"

    override fun handle(action: Action, entityId: EntityId?, data: Map<String, Any>?) {
        val entity = Reference.to(requireNotNull(entityId).id)
        when(entityId?.type) {
            EntityType.ITEM -> itemService.mutate(
                    entity,
                    ItemMutator.new().externalState(action.params).build()
            )
            EntityType.OBJECT -> objectService.mutate(
                    entity,
                    ObjectMutator.new().externalState(action.params).build()
            )
        }
    }
}