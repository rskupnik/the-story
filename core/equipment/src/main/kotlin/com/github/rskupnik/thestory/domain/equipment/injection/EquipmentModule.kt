package com.github.rskupnik.thestory.domain.equipment.injection

import com.github.rskupnik.thestory.domain.equipment.Equipment
import com.github.rskupnik.thestory.domain.equipment.internal.DefaultEquipment
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.event.EventDispatcher
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class EquipmentModule {

    @Provides @Singleton
    fun equipment(itemService: ItemService, eventDispatcher: EventDispatcher): Equipment = DefaultEquipment(itemService, eventDispatcher)
}