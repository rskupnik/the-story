package com.github.rskupnik.thestory.gameState

import com.github.rskupnik.thestory.gameState.internal.DefaultGameStateService

object GameStateInjectorHandle {

    fun service(): GameStateService = DefaultGameStateService()
}