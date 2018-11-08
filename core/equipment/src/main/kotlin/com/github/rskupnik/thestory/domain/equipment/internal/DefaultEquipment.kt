package com.github.rskupnik.thestory.domain.equipment.internal

import com.github.rskupnik.thestory.core.callback.domain.CallbackId
import com.github.rskupnik.thestory.core.callback.event.CallbackTriggeredEvent
import com.github.rskupnik.thestory.domain.equipment.Equipment
import com.github.rskupnik.thestory.item.domain.EquipmentSlot
import com.github.rskupnik.thestory.domain.item.ItemMutator
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.event.EventDispatcher
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.item.domain.ItemPlacement
import com.github.rskupnik.thestory.item.domain.ItemView
import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.entity.EntityId
import com.github.rskupnik.thestory.shared.entity.EntityType

internal class DefaultEquipment(
        private val itemService: ItemService,
        private val eventDispatcher: EventDispatcher,
        private val callbackReceiver: CallbackReceiver
) : Equipment {

    private val items: MutableMap<EquipmentSlot, Reference> = HashMap()

    override fun put(reference: Reference, slot: EquipmentSlot): Boolean {
        return if (itemExists(reference) && slotFree(slot)) {
            // Store the item
            items[slot] = reference

            // Mutate the item's placement param
            itemService.mutate(reference, ItemMutator.new().placement(ItemPlacement(Context.EQUIPMENT, slot)).build())

            // Execute the ON_EQUIP callback
            executeCallback(reference, CallbackId.ON_EQUIP)

            true
        } else false
    }

    override fun put(reference: Reference, allowedSlots: List<EquipmentSlot>?): Boolean {
        val slot = getFirstFreeSlot(allowedSlots)
        return if (slot != null) put(reference, slot) else false
    }

    override fun get(slot: EquipmentSlot): Reference? = items[slot]

    override fun getAll(): Map<EquipmentSlot, ItemView> = items
            .mapValues { itemService.getItemView(it.value) }
            .filterValues { it != null }
            .mapValues { it.value!! }

    override fun remove(reference: Reference): Boolean {
        val occupiedSlot = findOccupiedSlot(reference)
        return if (occupiedSlot != null) {
            items.remove(occupiedSlot) != null
        } else false
    }

    override fun refresh() {
        // Get all available items
        val existingItems = itemService.getAllItemsView()

        // Purge the existing storage
        items.clear()

        // Put all existing items in the storage if their placement is in inventory
        existingItems.forEach {
            if (it.placement != null && it.placement?.context == Context.EQUIPMENT) {
                val slot = it.placement?.slot as EquipmentSlot
                items[slot] = it.id.clone()
            }
        }

        callbackReceiver.onEquipmentRefreshed()
    }

    private fun findOccupiedSlot(reference: Reference): EquipmentSlot? = items
            .filter { it.value == reference }
            .keys.firstOrNull()

    private fun getFirstFreeSlot(allowedSlotsInput: List<EquipmentSlot>?): EquipmentSlot? {
        val allowedSlots = allowedSlotsInput ?: EquipmentSlot.values().asList()
        return allowedSlots.find { slotFree(it) }
    }

    private fun itemExists(reference: Reference) = itemService.getItemView(reference) != null

    private fun slotFree(slot: EquipmentSlot) = !items.contains(slot)

    private fun executeCallback(reference: Reference, callbackId: CallbackId) {
        val callback = callbackId.findInList(itemService.getCallbacks(reference))
        if (callback != null) {
            eventDispatcher.dispatch(CallbackTriggeredEvent(callback, EntityId(reference.value, EntityType.ITEM)))
        }
    }
}