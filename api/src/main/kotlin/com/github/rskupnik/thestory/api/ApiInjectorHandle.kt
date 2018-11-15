package com.github.rskupnik.thestory.api

import com.github.rskupnik.thestory.api.command.CommandAPI
import com.github.rskupnik.thestory.api.command.CommandAPIImplementation
import com.github.rskupnik.thestory.api.query.QueryAPI
import com.github.rskupnik.thestory.api.query.QueryAPIImplementationK
import com.github.rskupnik.thestory.background.BackgroundService
import com.github.rskupnik.thestory.domain.`object`.ObjectService
import com.github.rskupnik.thestory.domain.equipment.Equipment
import com.github.rskupnik.thestory.domain.inventory.Inventory
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.domain.npc.NpcService
import com.github.rskupnik.thestory.domain.option.OptionService
import com.github.rskupnik.thestory.domain.player.PlayerFacade
import com.github.rskupnik.thestory.event.EventDispatcher
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.persistence.PersistenceService
import com.github.rskupnik.thestory.script.ScriptService

object ApiInjectorHandle {
    fun queryAPI(itemService: ItemService, equipment: Equipment, inventory: Inventory): QueryAPI = QueryAPIImplementationK(
            itemService, equipment, inventory
    )

    fun commandAPI(
            itemService: ItemService,
            objectService: ObjectService,
            npcService: NpcService,
            moduleService: ModuleService,
            playerFacade: PlayerFacade,
            persistenceService: PersistenceService,
            backgroundService: BackgroundService,
            scriptService: ScriptService,
            optionService: OptionService,
            equipment: Equipment,
            inventory: Inventory,
            callbackReceiver: CallbackReceiver,
            eventDispatcher: EventDispatcher
    ): CommandAPI = CommandAPIImplementation(
            itemService, objectService, npcService, moduleService, playerFacade, persistenceService, backgroundService,
            scriptService, optionService, equipment, inventory, callbackReceiver, eventDispatcher
    )
}