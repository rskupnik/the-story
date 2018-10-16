package com.github.rskupnik.thestory.api.command

import com.github.rskupnik.thestory.domain.Direction
import com.github.rskupnik.thestory.domain.LocationId
import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.entity.EntityType

interface CommandAPI {

    fun clickItem(id: String, context: Context)

    fun clickObject(id: String)

    //fun executeConsole(id: String, params: Array<String>)

    fun initializeGame(module: String)

    /*fun instantiateNpc(npc: String, location: LocationId)

    fun instantiateObject(id: String, blueprintId: String, unique: Boolean)

    fun loadGame(filename: String)

    fun saveGame()

    fun loadLocation(location: LocationId)

    fun movePlayer(direction: Direction)

    fun selectOption(id: String, type: EntityType, optionId: String, context: Context)*/
}