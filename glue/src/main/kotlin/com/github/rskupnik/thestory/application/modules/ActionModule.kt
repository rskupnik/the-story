package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.action.handler.*
import com.github.rskupnik.thestory.application.internal.ActionInitializer
import com.github.rskupnik.thestory.background.BackgroundService
import com.github.rskupnik.thestory.domain.`object`.ObjectService
import com.github.rskupnik.thestory.domain.action.ActionExecutor
import com.github.rskupnik.thestory.domain.action.ActionInjectorHandle
import com.github.rskupnik.thestory.domain.equipment.Equipment
import com.github.rskupnik.thestory.domain.inventory.Inventory
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.event.EventDispatcher
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class ActionModule {

    @Provides @Singleton
    fun executor(
            itemService: ItemService,
            objectService: ObjectService,
            eventDispatcher: EventDispatcher
    ): ActionExecutor = ActionInjectorHandle.executor(itemService, objectService, eventDispatcher)

    @Provides @Singleton
    fun actionInitializer(
            equipItemActionHandler: EquipItemActionHandler,
            findItemActionHandler: FindItemActionHandler,
            removeItemActionHandler: RemoveItemActionHandler,
            changeImageActionHandler: ChangeImageActionHandler,
            setBackgroundActionHandler: SetBackgroundActionHandler,
            changeStateActionHandler: ChangeStateActionHandler
    ): ActionInitializer = ActionInitializer()

    @Provides @Singleton
    fun equipItemActionHandler(actionExecutor: ActionExecutor, equipment: Equipment, itemService: ItemService,
                               eventDispatcher: EventDispatcher, callbackReceiver: CallbackReceiver
    ): EquipItemActionHandler = ActionHandlerInjectorHandle
            .equipItemActionHandler(actionExecutor, equipment, itemService, eventDispatcher, callbackReceiver)

    @Provides @Singleton
    fun findItemActionHandler(actionExecutor: ActionExecutor, itemService: ItemService, inventory: Inventory,
                              callbackReceiver: CallbackReceiver): FindItemActionHandler =
            ActionHandlerInjectorHandle.findItemActionHandler(actionExecutor, itemService, inventory, callbackReceiver)

    @Provides @Singleton
    fun removeItemActionHandler(
            actionExecutor: ActionExecutor,
            itemService: ItemService,
            inventory: Inventory,
            equipment: Equipment,
            callbackReceiver: CallbackReceiver
    ): RemoveItemActionHandler = ActionHandlerInjectorHandle.removeItemActionHandler(
            actionExecutor, itemService, inventory, equipment, callbackReceiver
    )

    @Provides @Singleton
    fun changeImageActionHandler(
            actionExecutor: ActionExecutor,
            itemService: ItemService,
            callbackReceiver: CallbackReceiver
    ): ChangeImageActionHandler = ActionHandlerInjectorHandle.changeImageActionHandler(
            actionExecutor, itemService, callbackReceiver
    )

    @Provides @Singleton
    fun setBackgroundActionHandler(
            actionExecutor: ActionExecutor,
            backgroundService: BackgroundService
    ): SetBackgroundActionHandler = ActionHandlerInjectorHandle.setBackgroundActionHandler(
            actionExecutor, backgroundService
    )

    @Provides @Singleton
    fun changeStateActionHandler(
            actionExecutor: ActionExecutor,
            itemService: ItemService,
            objectService: ObjectService
    ): ChangeStateActionHandler = ActionHandlerInjectorHandle.changeStateActionHandler(
            actionExecutor, itemService, objectService
    )
}