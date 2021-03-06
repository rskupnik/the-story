package com.github.rskupnik.thestory.api.command

import com.github.rskupnik.thestory.api.command.details.background.BackgroundDetails
import com.github.rskupnik.thestory.api.command.details.background.NormalMappedBackgroundDetails
import com.github.rskupnik.thestory.background.BackgroundService
import com.github.rskupnik.thestory.core.console.ConsoleService
import com.github.rskupnik.thestory.domain.LocationId
import com.github.rskupnik.thestory.domain.`object`.ObjectService
import com.github.rskupnik.thestory.domain.equipment.Equipment
import com.github.rskupnik.thestory.domain.inventory.Inventory
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.domain.npc.NpcMutator
import com.github.rskupnik.thestory.domain.npc.NpcService
import com.github.rskupnik.thestory.domain.option.OptionService
import com.github.rskupnik.thestory.domain.player.PlayerFacade
import com.github.rskupnik.thestory.event.EventDispatcher
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.gameState.GameStateService
import com.github.rskupnik.thestory.gamestate.domain.GamePhase
import com.github.rskupnik.thestory.option.domain.Option
import com.github.rskupnik.thestory.persistence.PersistenceService
import com.github.rskupnik.thestory.script.ScriptService
import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.Direction
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.entity.EntityId
import com.github.rskupnik.thestory.shared.entity.EntityType
import com.github.rskupnik.wordplay.output.WordplayOutput

internal class CommandAPIImplementation(
        private val itemService: ItemService,
        private val objectService: ObjectService,
        private val npcService: NpcService,
        private val moduleService: ModuleService,
        private val playerFacade: PlayerFacade,
        private val gameStateService: GameStateService,
        private val persistenceService: PersistenceService,
        private val backgroundService: BackgroundService,
        private val scriptService: ScriptService,
        private val optionService: OptionService,
        private val equipment: Equipment,
        private val inventory: Inventory,
        private val consoleService: ConsoleService,
        private val callbackReceiver: CallbackReceiver,
        private val eventDispatcher: EventDispatcher
) : CommandAPI {

    override fun clickItem(id: String, context: Context) {
        val options = itemService.getOptions(Reference.to(id), context)
        callbackReceiver.onDisplayOptions(Option.optionsToLabels(EntityId(id, EntityType.ITEM), options, context))
    }

    override fun clickObject(id: String) {
        val options = objectService.getOptions(Reference.to(id))
        callbackReceiver.onDisplayOptions(Option.optionsToLabels(EntityId(id, EntityType.OBJECT), options))
    }

    override fun executeConsole(cmd: String) {
        consoleService.execute(cmd)
    }

    override fun initializeGame(module: String) {
        if (!gameStateService.gameAtPhase(listOf(GamePhase.UNINITIALIZED)))
            return

        for (moduleRef in moduleService.load(module)) {
            objectService.loadBlueprints(moduleRef)
            itemService.loadBlueprints(moduleRef)
            npcService.loadBlueprints(moduleRef)
        }

        gameStateService.setPhase(GamePhase.RUNNING)
    }

    override fun instantiateNpc(npc: String, location: LocationId) {
        with(npcService) {
            instantiate(Reference.to(npc))
            mutate(Reference.to(npc), NpcMutator.new().location(location).build())
        }
    }

    override fun instantiateObject(id: String, blueprintId: String, unique: Boolean) {
        val method = if (unique) objectService::instantiateUnique else objectService::instantiate
        method.invoke(id, Reference.to(blueprintId))
    }

    override fun loadGame(filename: String) {
        if (!gameStateService.gameAtPhase(listOf(GamePhase.UNINITIALIZED)))
            return

        val state = persistenceService.readState(filename)

        initializeGame(state["module"] as String)

        // Load data
        persistenceService.loadState(state)

        // Refresh equipment and inventory
        equipment.refresh()
        inventory.refresh()

        gameStateService.setPhase(GamePhase.RUNNING)
    }

    override fun saveGame() {
        persistenceService.save()
    }

    override fun loadLocation(location: LocationId): Boolean {
        // Load and parse the wordplay script
        val wordplayOutput: WordplayOutput = scriptService.loadLocation(location) ?: return false

        // Transform the wordplay script into an externally available output
        val parsedScript = scriptService.parse(wordplayOutput)

        // Instantiate unique objects
        for (obj in wordplayOutput.anchoredObjects) {
            val id = obj.getStringParam("id") ?: continue
            val definitionId = obj.getStringParam("def") ?: continue
            objectService.instantiateUnique(id, Reference.to(definitionId))
        }

        callbackReceiver.onLocationLoaded(parsedScript)

        return true
    }

    override fun movePlayer(direction: Direction) {
        // Calculate the target location
        val targetLocation = playerFacade.getCurrentLocation().applyDirection(direction)

        // Load the target location
        if (loadLocation(targetLocation)) {
            // Update the player's position
            playerFacade.setCurrentLocation(targetLocation)
        }
    }

    override fun selectOption(id: String, type: EntityType, optionId: String, context: Context?) {
        val option = getOption(optionId, id, type, context) ?: return
        optionService.execute(option, null, context, EntityId(id, type))
    }

    override fun setBackground(background: BackgroundDetails?) = when (background) {
        is NormalMappedBackgroundDetails -> backgroundService.setNormalMappedBackground(background.image, background.normalImage)
        null -> backgroundService.setNoBackground()
        else -> {}  // Do nothing
    }

    private fun getOption(optionId: String, entityId: String, entityType: EntityType, context: Context?): Option? {
        val options = when(entityType) {
            EntityType.OBJECT -> objectService.getOptions(Reference.to(entityId))
            EntityType.ITEM -> itemService.getOptions(Reference.to(entityId), context)
            else -> return null
        }

        return options.find { it.id == optionId }
    }
}