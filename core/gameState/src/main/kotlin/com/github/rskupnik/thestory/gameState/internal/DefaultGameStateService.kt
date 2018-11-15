package com.github.rskupnik.thestory.gameState.internal

import com.github.rskupnik.thestory.background.domain.Background
import com.github.rskupnik.thestory.gameState.GameStateService
import com.github.rskupnik.thestory.persistence.PersistenceSubscriber

internal class DefaultGameStateService(
        persistenceSubscriber: PersistenceSubscriber
): GameStateService {

    override val persistenceKey = "gameState"

    private val gameState = GameState()

    init {
        persistenceSubscriber.subscribe(this)
    }

    override fun setBackground(background: Background?) {
        gameState.background = background
    }

    override fun getBackground(): Background? = gameState.background?.clone()

    //region PERSISTENCE
    override fun produceState(): Any = mapOf(
            "background" to gameState.background?.toPersistableState()
    )

    override fun ingestState(state: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    //endregion
}