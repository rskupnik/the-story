package com.github.rskupnik.thestory.domain.inventory.injection

import com.github.rskupnik.thestory.domain.inventory.Inventory
import com.github.rskupnik.thestory.domain.inventory.internal.DefaultInventory
import com.github.rskupnik.thestory.domain.item.ItemService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InventoryModule {

    @Provides @Singleton
    fun inventory(itemService: ItemService): Inventory = DefaultInventory(itemService)
}