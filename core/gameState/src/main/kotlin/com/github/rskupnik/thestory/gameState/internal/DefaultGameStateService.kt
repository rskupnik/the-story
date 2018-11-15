package com.github.rskupnik.thestory.gameState.internal

import com.github.rskupnik.thestory.background.domain.Background
import com.github.rskupnik.thestory.gameState.GameStateService

internal class DefaultGameStateService: GameStateService {

    override val persistenceKey = "gameState"

    private val gameState = GameState()

    override fun setBackground(background: Background?) {
        gameState.background = background
    }

    override fun getBackground(): Background? = gameState.background?.clone()

    //region PERSISTENCE
    override fun produceState(): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun ingestState(state: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    //endregion
}