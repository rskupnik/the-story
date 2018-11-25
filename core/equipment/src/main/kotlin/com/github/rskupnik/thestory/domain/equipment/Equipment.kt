package com.github.rskupnik.thestory.domain.equipment

import com.github.rskupnik.thestory.item.domain.EquipmentSlot
import com.github.rskupnik.thestory.item.domain.ItemView
import com.github.rskupnik.thestory.shared.Reference

/**
 * Contains logic for manipulating the player's equipment.
 */
interface Equipment {

    fun put(reference: Reference, slot: EquipmentSlot): Boolean

    fun put(reference: Reference, allowedSlots: List<EquipmentSlot>? = null): Boolean

    fun get(slot: EquipmentSlot): Reference?

    fun getAll(): Map<EquipmentSlot, ItemView>

    fun remove(reference: Reference): Boolean

    fun refresh()
}