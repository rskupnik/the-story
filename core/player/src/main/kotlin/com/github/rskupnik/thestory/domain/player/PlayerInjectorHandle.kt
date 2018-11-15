package com.github.rskupnik.thestory.domain.player

import com.github.rskupnik.thestory.domain.player.internal.DefaultPlayerService
import com.github.rskupnik.thestory.persistence.PersistenceSubscriber

object PlayerInjectorHandle {
    fun service(
            persistenceSubscriber: PersistenceSubscriber
    ): PlayerFacade = DefaultPlayerService(persistenceSubscriber)
}