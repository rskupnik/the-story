package com.github.rskupnik.thestory.action.handler

import com.github.rskupnik.thestory.action.domain.Action
import com.github.rskupnik.thestory.domain.action.AbstractActionHandler
import com.github.rskupnik.thestory.domain.action.ActionExecutor
import com.github.rskupnik.thestory.domain.equipment.Equipment
import com.github.rskupnik.thestory.domain.inventory.Inventory
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.entity.EntityId

/**
 * Handles removing an item from equipment and putting it into inventory.
 *
 * Will fail if there is no free slot found in inventory.
 */
class RemoveItemActionHandler(
        actionExecutor: ActionExecutor,
        private val equipment: Equipment,
        private val inventory: Inventory,
        private val itemService: ItemService,
        private val callbackReceiver: CallbackReceiver
): AbstractActionHandler(actionExecutor) {
    override fun id(): String = "removeItem"

    override fun handle(action: Action, entityId: EntityId?, data: Map<String, Any>?) {
        val entity: Reference = Reference.to(requireNotNull(entityId).id)

        // Stop if no free slot is found in inventory
        if (inventory.findFreeSlot() == null) {
            return
        }

        val itemView = requireNotNull(itemService.getItemView(entity))

        if (equipment.remove(entity)) {
            inventory.put(entity)
            callbackReceiver.onItemRemoved(itemView)
        }
    }
}