package com.github.rskupnik.thestory.gameState.internal

import com.github.rskupnik.thestory.gameState.GameStateService
import com.github.rskupnik.thestory.gamestate.domain.GamePhase

internal class DefaultGameStateService : GameStateService {
    private val gameState = GameState()

    override fun setPhase(phase: GamePhase) {
        gameState.phase = phase
    }

    override fun gameAtPhase(allowedPhases: List<GamePhase>): Boolean = allowedPhases.any { it == gameState.phase }
}