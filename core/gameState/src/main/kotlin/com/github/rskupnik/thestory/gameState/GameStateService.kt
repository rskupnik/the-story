package com.github.rskupnik.thestory.gameState

import com.github.rskupnik.thestory.gamestate.domain.GamePhase

interface GameStateService {

    fun setPhase(phase: GamePhase)

    fun gameAtPhase(allowedPhases: List<GamePhase>): Boolean
}