package com.github.rskupnik.thestory.gameState.internal

import com.github.rskupnik.thestory.background.domain.Background
import com.github.rskupnik.thestory.gameState.GameStateService

internal class DefaultGameStateService: GameStateService {

    private val gameState = GameState()

    override fun setBackground(background: Background?) {
        gameState.background = background
    }

    override fun getBackground(): Background? = gameState.background?.clone()
}