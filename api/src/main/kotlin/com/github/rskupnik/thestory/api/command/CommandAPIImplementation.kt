package com.github.rskupnik.thestory.api.command

import com.github.rskupnik.thestory.api.command.details.background.BackgroundDetails
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
//        private val gameStateFacade: GameStateFacade,
        private val persistenceService: PersistenceService,
//        private val backgroundService: BackgroundService,
        private val scriptService: ScriptService,
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

    override fun loadGame(filename: String) {
        // TODO: GameState
        //if (!gameStateFacade.gameAtPhase(listOf(GamePhase.UNINITIALIZED)))
        //    return

        val state = persistenceService.readState(filename)

        // TODO Initialize game state
        initializeGame(state["module"] as String)

        // TODO Set player location
//        val playerData = state.get("player") as Map<String, Any>
//        val location = playerData["location"] as Map<String, Any>
//        val locationId = LocationId(location["zone"] as String, location["x"] as Int, location["y"] as Int)
//        playerFacade.currentLocation = locationId
//        loadLocation(locationId)

        // TODO Load game state
//        loadGameState(state)

        // Load data
        persistenceService.loadState(state)

        // Refresh equipment and inventory
        equipment.refresh()
        inventory.refresh()

        // TODO Set status: RUNNING
//        gameStateFacade.phase = GamePhase.RUNNING
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

    override fun setBackground(background: BackgroundDetails?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /*private fun loadGameState(snapshot: Map<String, Any>) {
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
    }*/

    private fun getOption(optionId: String, entityId: String, entityType: EntityType, context: Context?): Option? {
        val options = when(entityType) {
            EntityType.OBJECT -> objectService.getOptions(Reference.to(entityId))
            EntityType.ITEM -> itemService.getOptions(Reference.to(entityId), context)
            else -> return null
        }

        return options.find { it.id == optionId }
    }
}