package com.github.rskupnik.thestory.gameState

import com.github.rskupnik.thestory.gameState.internal.DefaultGameStateService
import com.github.rskupnik.thestory.persistence.PersistenceSubscriber

object GameStateInjectorHandle {

    fun service(
            persistenceSubscriber: PersistenceSubscriber
    ): GameStateService = DefaultGameStateService(persistenceSubscriber)
}