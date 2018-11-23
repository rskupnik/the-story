package com.github.rskupnik.thestory.api.command

import com.github.rskupnik.thestory.api.command.details.background.BackgroundDetails
import com.github.rskupnik.thestory.shared.Direction
import com.github.rskupnik.thestory.domain.LocationId
import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.entity.EntityType

/**
 * The main interface for issuing commands for the application to perform.
 *
 * This is the place where the higher layers can issue commands for the application to execute.
 * In simple words - this is where you tell the game to do stuff.
 */
interface CommandAPI {

    /**
     * Click an item identified by [id] in a provided [context].
     *
     * This should be called when the user click and item icon in either inventory or equipment.
     * @param context informs whether the click was done in inventory or in equipment
     */
    fun clickItem(id: String, context: Context)

    /**
     * Click and object in the game world identified by [id].
     */
    fun clickObject(id: String)

    /**
     * Execute a console command ([cmd]).
     */
    fun executeConsole(cmd: String)


    /**
     * Initialize the game by loading the specified [module].
     *
     * This is on of the two commands that need to be made first to start the game.
     * The other option is [loadGame].
     */
    fun initializeGame(module: String)

    /**
     * Instantiate an [npc] in the specified [location].
     */
    fun instantiateNpc(npc: String, location: LocationId)

    /**
     * Instantiate an object from a blueprint ([blueprintId]) and give it the [id].
     *
     * If [unique] is true, no object will be created if an object with this [id] already exists.
     * Should be used for objects with pre-defined [id], for example in wordplay scripts.
     */
    fun instantiateObject(id: String, blueprintId: String, unique: Boolean)

    /**
     * Load a game from a save file indicated by [filename].
     *
     * This is one of the functions that need to be called first to start the game.
     * The other option is [initializeGame].
     */
    fun loadGame(filename: String)

    /**
     * Save game state to a file - the name of the file depends on the name of the main module.
     */
    fun saveGame()

    /**
     * Attempt to load a [location].
     *
     * This will trigger [com.github.rskupnik.thestory.external.feedback.CallbackReceiver.onLocationLoaded] with details
     * of the new location - the higher layer should react to this by displaying the proper information to the player.
     *
     * @return a boolean indicating whether the location was loaded or not (it might be that the location was not found)
     */
    fun loadLocation(location: LocationId): Boolean

    /**
     * Move the player in the specified [direction] (if possible).
     *
     * This will calculate the new location and try to load it (using [loadLocation]) and will update the player's
     * current position if location was found. Otherwise nothing will change.
     */
    fun movePlayer(direction: Direction)

    /**
     * Execute an option identified by [optionId] on an entity identified by [id] and by [type].
     *
     * And entity can either be an `OBJECT` or an `ITEM`. Also uses [context] in case of an `ITEM`.
     */
    fun selectOption(id: String, type: EntityType, optionId: String, context: Context?)

    /**
     * Set the background - will set to no background if `null` is passed.
     */
    fun setBackground(background: BackgroundDetails?)
}