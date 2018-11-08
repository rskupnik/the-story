package com.github.rskupnik.thestory.api.query

import com.github.rskupnik.thestory.control.query.result.*
import com.github.rskupnik.thestory.domain.equipment.Equipment
import com.github.rskupnik.thestory.item.domain.EquipmentSlot
import com.github.rskupnik.thestory.domain.inventory.Inventory
import com.github.rskupnik.thestory.item.domain.InventorySlot
import com.github.rskupnik.thestory.domain.item.ItemService

internal class QueryAPIImplementationK(
        private val itemService: ItemService,
        private val equipment: Equipment,
        private val inventory: Inventory
) : QueryAPI {

    override fun getAvailableModules(): AvailableModules {
        return AvailableModules(emptyList())
    }

    override fun getEquipmentContent(): EquipmentContent {
        return EquipmentContent(equipment.getAll())
    }

    override fun getSingleItemFromEquipment(slot: EquipmentSlot): EquipmentSingleItem {
        return EquipmentSingleItem(itemService.getItemView(equipment.get(slot) ?: return EquipmentSingleItem(null)))
    }

    override fun getInventoryContent(): InventoryContent {
        return InventoryContent(inventory.getAll())
    }

    override fun getSingleItemFromInventory(slot: InventorySlot): InventorySingleItem {
        return InventorySingleItem(itemService.getItemView(inventory.get(slot) ?: return InventorySingleItem(null)))
    }
}