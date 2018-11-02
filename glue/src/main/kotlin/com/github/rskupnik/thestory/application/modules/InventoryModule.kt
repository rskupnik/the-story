package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.domain.inventory.Inventory
import com.github.rskupnik.thestory.domain.inventory.InventoryInjectorHandle
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.event.EventDispatcher
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InventoryModule {

    @Provides @Singleton
    fun inventory(itemService: ItemService, callbackReceiver: CallbackReceiver,
                  eventDispatcher: EventDispatcher): Inventory =
            InventoryInjectorHandle.inventory(itemService, callbackReceiver, eventDispatcher)
}