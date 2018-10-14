package com.github.rskupnik.thestory.domain.equipment

import com.github.rskupnik.thestory.domain.equipment.internal.DefaultEquipment
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.event.EventDispatcher

object EquipmentInjectorHandle {
    fun equipment(itemService: ItemService, eventDispatcher: EventDispatcher): Equipment = DefaultEquipment(itemService, eventDispatcher)
}