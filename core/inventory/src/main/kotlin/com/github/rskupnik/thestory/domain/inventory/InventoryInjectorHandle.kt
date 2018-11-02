package com.github.rskupnik.thestory.domain.inventory

import com.github.rskupnik.thestory.domain.inventory.internal.DefaultInventory
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.event.EventDispatcher
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver

object InventoryInjectorHandle {
    fun inventory(itemService: ItemService, callbackReceiver: CallbackReceiver,
                  eventDispatcher: EventDispatcher): Inventory = DefaultInventory(
            itemService, callbackReceiver, eventDispatcher
    )
}