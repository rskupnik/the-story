package com.github.rskupnik.thestory.action.handler

import com.github.rskupnik.thestory.background.BackgroundService
import com.github.rskupnik.thestory.domain.action.ActionExecutor
import com.github.rskupnik.thestory.domain.equipment.Equipment
import com.github.rskupnik.thestory.domain.inventory.Inventory
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.event.EventDispatcher
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver

object ActionHandlerInjectorHandle {

    fun equipItemActionHandler(actionExecutor: ActionExecutor, equipment: Equipment,
                               itemService: ItemService, eventDispatcher: EventDispatcher,
                               callbackReceiver: CallbackReceiver): EquipItemActionHandler = EquipItemActionHandler(
            actionExecutor, equipment, itemService, eventDispatcher, callbackReceiver
    )

    fun findItemActionHandler(actionExecutor: ActionExecutor, itemService: ItemService,
                               inventory: Inventory, callbackReceiver: CallbackReceiver): FindItemActionHandler =
            FindItemActionHandler(actionExecutor, itemService, inventory, callbackReceiver)

    fun removeItemActionHandler(
            actionExecutor: ActionExecutor,
            itemService: ItemService,
            inventory: Inventory,
            equipment: Equipment,
            callbackReceiver: CallbackReceiver
    ): RemoveItemActionHandler = RemoveItemActionHandler(
            actionExecutor, equipment, inventory, itemService, callbackReceiver
    )

    fun changeImageActionHandler(
            actionExecutor: ActionExecutor,
            itemService: ItemService,
            callbackReceiver: CallbackReceiver
    ): ChangeImageActionHandler = ChangeImageActionHandler(
            actionExecutor, itemService, callbackReceiver
    )

    fun setBackgroundActionHandler(
            actionExecutor: ActionExecutor,
            backgroundService: BackgroundService
    ): SetBackgroundActionHandler = SetBackgroundActionHandler(
            actionExecutor, backgroundService
    )
}