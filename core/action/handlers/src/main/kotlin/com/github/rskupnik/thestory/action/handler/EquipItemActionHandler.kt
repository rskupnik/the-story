package com.github.rskupnik.thestory.action.handler

import com.github.rskupnik.thestory.action.domain.Action
import com.github.rskupnik.thestory.domain.action.AbstractActionHandler
import com.github.rskupnik.thestory.domain.action.ActionExecutor
import com.github.rskupnik.thestory.domain.equipment.Equipment
import com.github.rskupnik.thestory.domain.equipment.EquipmentSlot
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.equipment.event.ItemEquippedEvent
import com.github.rskupnik.thestory.event.EventDispatcher
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.entity.EntityId

class EquipItemActionHandler(
        actionExecutor: ActionExecutor,
        private val equipment: Equipment,
        private val itemService: ItemService,
        private val eventDispatcher: EventDispatcher,
        private val callbackReceiver: CallbackReceiver
) : AbstractActionHandler(actionExecutor) {

    override fun id() = "equipItem"

    override fun handle(action: Action, entityId: EntityId?, data: Map<String, Any>?) {
        val entityId = requireNotNull(entityId)

        val item = requireNotNull(itemService.getItemView(Reference.to(entityId.id)))
        val allowedSlots = convertToAllowedSlots(requireNotNull(action.params["allowedSlots"]) as List<String>)

        // Find a free slot in equipment according to allowedSlots list and equip it
        val equipped = equipment.put(Reference.to(entityId.id), allowedSlots)

        if (equipped) {
            // Send an event so other item holders can check if they need to remove the item
            eventDispatcher.dispatch(ItemEquippedEvent(Reference.to(entityId.id)))

            // Inform the OutputReceiver so external user can refresh equipment and inventory
            callbackReceiver.onItemEquipped(item)
        }
    }

    private fun convertToAllowedSlots(possibleSlots: List<String>): List<EquipmentSlot> = possibleSlots
            .map { EquipmentSlot.valueOf(it.toUpperCase()) }
}