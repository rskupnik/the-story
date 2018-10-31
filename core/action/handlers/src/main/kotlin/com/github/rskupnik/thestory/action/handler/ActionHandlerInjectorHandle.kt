package com.github.rskupnik.thestory.action.handler

import com.github.rskupnik.thestory.domain.action.ActionExecutor
import com.github.rskupnik.thestory.domain.equipment.Equipment
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.event.EventDispatcher
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver

object ActionHandlerInjectorHandle {

    fun equipItemActionHandler(actionExecutor: ActionExecutor, equipment: Equipment,
                               itemService: ItemService, eventDispatcher: EventDispatcher,
                               callbackReceiver: CallbackReceiver): EquipItemActionHandler = EquipItemActionHandler(
            actionExecutor, equipment, itemService, eventDispatcher, callbackReceiver
    )
}