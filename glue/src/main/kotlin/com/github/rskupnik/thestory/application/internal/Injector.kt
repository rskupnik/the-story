package com.github.rskupnik.thestory.application.internal

import com.github.rskupnik.thestory.api.ApiModule
import com.github.rskupnik.thestory.domain.`object`.injection.ObjectModule
import com.github.rskupnik.thestory.domain.equipment.injection.EquipmentModule
import com.github.rskupnik.thestory.domain.inventory.injection.InventoryModule
import com.github.rskupnik.thestory.domain.item.injection.ItemModule
import com.github.rskupnik.thestory.domain.npc.injection.NpcModule
import com.github.rskupnik.thestory.domain.option.injection.OptionModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    ApiModule::class,
    ItemModule::class,
    ObjectModule::class,
    NpcModule::class,
    InventoryModule::class,
    EquipmentModule::class,
    OptionModule::class
])
@Singleton
internal interface Injector {
    fun root(): Root
}