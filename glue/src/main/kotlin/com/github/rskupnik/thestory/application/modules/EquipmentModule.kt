package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.domain.equipment.Equipment
import com.github.rskupnik.thestory.domain.equipment.EquipmentInjectorHandle
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.event.EventDispatcher
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class EquipmentModule {

    @Provides @Singleton
    fun equipment(itemService: ItemService, eventDispatcher: EventDispatcher): Equipment = EquipmentInjectorHandle.equipment(itemService, eventDispatcher)
}