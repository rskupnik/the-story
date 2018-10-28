package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.action.handler.ActionHandlerInjectorHandle
import com.github.rskupnik.thestory.action.handler.EquipItemActionHandler
import com.github.rskupnik.thestory.application.internal.ActionInitializer
import com.github.rskupnik.thestory.domain.action.ActionExecutor
import com.github.rskupnik.thestory.domain.action.ActionInjectorHandle
import com.github.rskupnik.thestory.domain.equipment.Equipment
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.event.EventDispatcher
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class ActionModule {

    @Provides @Singleton
    fun executor(itemService: ItemService, eventDispatcher: EventDispatcher): ActionExecutor = ActionInjectorHandle
            .executor(itemService, eventDispatcher)

    @Provides @Singleton
    fun actionInitializer(equipItemActionHandler: EquipItemActionHandler): ActionInitializer = ActionInitializer()

    @Provides @Singleton
    fun equipItemActionHandler(actionExecutor: ActionExecutor, equipment: Equipment): EquipItemActionHandler = ActionHandlerInjectorHandle
            .equipItemActionHandler(actionExecutor, equipment)
}