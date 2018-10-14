package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.domain.inventory.Inventory
import com.github.rskupnik.thestory.domain.inventory.InventoryInjectorHandle
import com.github.rskupnik.thestory.domain.item.ItemService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InventoryModule {

    @Provides @Singleton
    fun inventory(itemService: ItemService): Inventory = InventoryInjectorHandle.inventory(itemService)
}