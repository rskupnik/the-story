package com.github.rskupnik.thestory.application.internal

import com.github.rskupnik.thestory.application.adapter.AdaptersModule
import com.github.rskupnik.thestory.application.modules.*
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    ApiModule::class,
    ActionModule::class,
    EventModule::class,
    ItemModule::class,
    ObjectModule::class,
    NpcModule::class,
    InventoryModule::class,
    EquipmentModule::class,
    PlayerModule::class,
    OptionModule::class,
    JsonModule::class,
    AdaptersModule::class,
    ModulesModule::class,
    ScriptModule::class,
    GameStateModule::class,
    BackgroundModule::class
])
@Singleton
internal interface Injector {
    fun root(): Root
}