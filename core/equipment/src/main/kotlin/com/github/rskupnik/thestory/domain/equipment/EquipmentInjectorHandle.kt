package com.github.rskupnik.thestory.domain.equipment

import com.github.rskupnik.thestory.domain.equipment.internal.DefaultEquipment
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.event.EventDispatcher
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver

object EquipmentInjectorHandle {
    fun equipment(
            itemService: ItemService,
            eventDispatcher: EventDispatcher,
            callbackReceiver: CallbackReceiver
    ): Equipment = DefaultEquipment(itemService, eventDispatcher, callbackReceiver)
}