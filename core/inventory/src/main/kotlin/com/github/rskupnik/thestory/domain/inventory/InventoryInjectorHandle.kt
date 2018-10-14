package com.github.rskupnik.thestory.domain.inventory

import com.github.rskupnik.thestory.domain.inventory.internal.DefaultInventory
import com.github.rskupnik.thestory.domain.item.ItemService

object InventoryInjectorHandle {
    fun inventory(itemService: ItemService): Inventory = DefaultInventory(itemService)
}