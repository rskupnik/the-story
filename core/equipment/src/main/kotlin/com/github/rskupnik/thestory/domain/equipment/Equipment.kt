package com.github.rskupnik.thestory.domain.equipment

import com.github.rskupnik.thestory.item.domain.EquipmentSlot
import com.github.rskupnik.thestory.item.domain.ItemView
import com.github.rskupnik.thestory.shared.Reference

/**
 * Contains logic for manipulating the player's equipment.
 */
interface Equipment {

    /**
     * Put an item ([reference]) into a particular [slot].
     *
     * @return a Boolean indicating whether the item was equipped or not
     */
    fun put(reference: Reference, slot: EquipmentSlot): Boolean

    /**
     * Put an item ([reference]) into first free slot found among the [allowedSlots].
     *
     * @return a Boolean indicating whether the item was equipped or not
     */
    fun put(reference: Reference, allowedSlots: List<EquipmentSlot>? = null): Boolean

    /**
     * Get an item [Reference] assigned to the specified [slot].
     */
    fun get(slot: EquipmentSlot): Reference?

    /**
     * Get an immutable [Map] of all slots and the items that are in them.
     */
    fun getAll(): Map<EquipmentSlot, ItemView>

    /**
     * Remove an item ([reference]) if it is equipped.
     *
     * @return a Boolean indicating whether the item was removed or not
     */
    fun remove(reference: Reference): Boolean

    /**
     * Refresh the equipment state - purge all information and refresh it based on each existing item's placement.
     *
     * Usually done after a game is loaded.
     */
    fun refresh()
}