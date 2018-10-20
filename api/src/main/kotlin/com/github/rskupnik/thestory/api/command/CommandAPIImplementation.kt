package com.github.rskupnik.thestory.api.command

import com.github.rskupnik.thestory.domain.Direction
import com.github.rskupnik.thestory.domain.LocationId
import com.github.rskupnik.thestory.shared.external.dto.OptionLabel
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
import com.github.rskupnik.thestory.option.domain.Option
import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.entity.EntityId
import com.github.rskupnik.thestory.shared.entity.EntityType
import com.github.rskupnik.thestory.shared.external.CallbackReceiver

internal class CommandAPIImplementation(
        private val itemService: ItemService,
        private val objectService: ObjectService,
        private val npcService: NpcService,
        private val moduleService: ModuleService,
        private val playerFacade: PlayerFacade,
//        private val gameStateFacade: GameStateFacade,
//        private val persistenceFacade: PersistenceFacade,
//        private val backgroundService: BackgroundService,
//        private val wordplayFacade: WordplayFacade,
        private val optionService: OptionService,
        private val equipment: Equipment,
        private val inventory: Inventory,
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

    /*override fun executeConsole(id: String, params: Array<String>) {
        println("EXECUTE CONSOLE KOTLIN")
        eventDispatcher.dispatch(ExecuteConsoleCommandEvent(id, params))
    }*/

    override fun initializeGame(module: String) {
//        if (!gameStateFacade.gameAtPhase(listOf(GamePhase.UNINITIALIZED)))
//            return

        for (moduleRef in moduleService.load(module)) {
            objectService.loadBlueprints(moduleRef)
            itemService.loadBlueprints(moduleRef)
            npcService.loadBlueprints(moduleRef)
        }

//        gameStateFacade.phase = GamePhase.RUNNING
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

    /*override fun loadGame(filename: String) {
        // TODO: GameState
        //if (!gameStateFacade.gameAtPhase(listOf(GamePhase.UNINITIALIZED)))
        //    return

        val snapshot = persistenceFacade.load(filename) ?: return

        // Initialize game state
        initializeGame(snapshot.get("module") as String)

        // Set player location
        val playerData = snapshot.get("player") as Map<String, Any>
        val location = playerData["location"] as Map<String, Any>
        val locationId = LocationId(location["zone"] as String, location["x"] as Int, location["y"] as Int)
        playerFacade.currentLocation = locationId
        loadLocation(locationId)

        // Load game state
        loadGameState(snapshot)

        // Load data
        persistenceFacade.loadData(snapshot)

        // Refresh equipment and inventory
        equipment.refresh()
        inventory.refresh()

        // Set status: RUNNING
        gameStateFacade.phase = GamePhase.RUNNING
    }

    override fun saveGame() {
        println("SAVE GAME KOTLIN")
        persistenceFacade.save()
    }*/

    override fun loadLocation(location: LocationId) {
        println("LOAD LOCATION KOTLIN")
        // Load and parse the wordplay script
        val wordplayOutputOpt = wordplayFacade.loadLocation(location)
        val wordplayOutput = if (wordplayOutputOpt.isPresent) wordplayOutputOpt.get() else return

        // Transform the wordplay script into an externally available output
        val parsedScript = wordplayFacade.parse(wordplayOutput)

        // Instantiate unique objects
        for (obj in wordplayOutput.getAnchoredObjects()) {
            val id = obj.getStringParam("id")
            val definitionId = obj.getStringParam("def")
            if (id == null || definitionId == null) {
                continue
            }

            objectService.instantiateUnique(id, Reference.from(definitionId))
        }

        outputReceiver.onLocationLoaded(parsedScript)
    }

    override fun movePlayer(direction: Direction) {
        println("MOVE PLAYER KOTLIN")
        // Calculate the target location
        val targetLocation = playerFacade.getCurrentLocation().applyDirection(direction)

        // Load the target location - TODO: check result
        // TODO: uncomment when loadLocation is ready
        //loadLocation(targetLocation)

        // Update the player's position
        playerFacade.setCurrentLocation(targetLocation)
    }

    /*override fun selectOption(id: String, type: EntityType, optionId: String, context: Context) {
        println("SELECT OPTION KOTLIN")
        val option = getOption(optionId, id, type, context) ?: return
        optionService.execute(option, null, context, EntityIdentifier(id, type))
    }

    private fun loadGameState(snapshot: Map<String, Any>) {
        val gameState = snapshot["gameState"] as Map<String, Any>
        restoreBackground(gameState)
    }

    private fun restoreBackground(gameState: Map<String, Any>) {
        val background = gameState["background"] as Map<String, Any>
        val type = BackgroundType.valueOf(background["type"] as String)
        when (type) {
            BackgroundType.EMPTY -> backgroundService.setNoBackground()
            BackgroundType.NORMAL_MAPPED ->
                backgroundService.setNormalMappedBackground(background["image"] as String,
                        background["normalImage"] as String)
        }
    }

    private fun getOption(optionId: String, entityId: String, entityType: EntityType, context: Context): Option? {
        val options = when(entityType) {
            EntityType.OBJECT -> objectService.getOptions(Reference.from(entityId))
            EntityType.ITEM -> itemService.getOptions(Reference.from(entityId), context)
            else -> return null
        }

        for (option in options) {
            if (option.id.equals(optionId)) {
                return option
            }
        }

        return null
    }*/
}