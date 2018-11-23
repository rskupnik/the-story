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

    fun clickItem(id: String, context: Context)

    fun clickObject(id: String)

    fun executeConsole(cmd: String)

    fun initializeGame(module: String)

    fun instantiateNpc(npc: String, location: LocationId)

    fun instantiateObject(id: String, blueprintId: String, unique: Boolean)

    fun loadGame(filename: String)

    fun saveGame()

    fun loadLocation(location: LocationId): Boolean

    fun movePlayer(direction: Direction)

    fun selectOption(id: String, type: EntityType, optionId: String, context: Context?)

    fun setBackground(background: BackgroundDetails?)
}