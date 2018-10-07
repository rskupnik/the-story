package com.github.rskupnik.thestory.api.query

import com.github.rskupnik.thestory.control.query.result.*
import com.github.rskupnik.thestory.domain.equipment.EquipmentSlot
import com.github.rskupnik.thestory.domain.inventory.InventorySlot

interface QueryAPI {

    fun getAvailableModules(): AvailableModules

    fun getEquipmentContent(): EquipmentContent

    fun getSingleItemFromEquipment(slot: EquipmentSlot): EquipmentSingleItem

    fun getInventoryContent(): InventoryContent

    fun getSingleItemFromInventory(slot: InventorySlot): InventorySingleItem
}