package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.api.ApiInjectorHandle
import com.github.rskupnik.thestory.api.command.CommandAPI
import com.github.rskupnik.thestory.api.query.QueryAPI
import com.github.rskupnik.thestory.domain.`object`.ObjectService
import com.github.rskupnik.thestory.domain.equipment.Equipment
import com.github.rskupnik.thestory.domain.inventory.Inventory
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.domain.npc.NpcService
import com.github.rskupnik.thestory.domain.option.OptionService
import com.github.rskupnik.thestory.domain.player.PlayerFacade
import com.github.rskupnik.thestory.event.EventDispatcher
import com.github.rskupnik.thestory.external.CallbackReceiver
import com.github.rskupnik.thestory.script.ScriptService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides @Singleton
    fun queryAPI(itemService: ItemService, equipment: Equipment, inventory: Inventory): QueryAPI = ApiInjectorHandle.queryAPI(
            itemService, equipment, inventory
    )

    @Provides @Singleton
    fun commandAPI(itemService: ItemService, objectService: ObjectService, npcService: NpcService,
                   moduleService: ModuleService, playerFacade: PlayerFacade, optionService: OptionService, scriptService: ScriptService,
                   equipment: Equipment, inventory: Inventory, callbackReceiver: CallbackReceiver, eventDispatcher: EventDispatcher
    ): CommandAPI = ApiInjectorHandle.commandAPI(
            itemService, objectService, npcService, moduleService, playerFacade, scriptService, optionService, equipment, inventory, callbackReceiver, eventDispatcher
    )
}