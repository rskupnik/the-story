package com.github.rskupnik.thestory.gameState.internal

import com.github.rskupnik.thestory.background.domain.Background
import com.github.rskupnik.thestory.gameState.GameStateService

internal class DefaultGameStateService: GameStateService {

    override fun setBackground(background: Background?) {
        GameState.background = background
    }

    override fun getBackground(): Background? = GameState.background?.clone()
}