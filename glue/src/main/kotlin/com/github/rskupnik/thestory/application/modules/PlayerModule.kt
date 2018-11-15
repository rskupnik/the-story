package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.domain.player.PlayerFacade
import com.github.rskupnik.thestory.domain.player.PlayerInjectorHandle
import com.github.rskupnik.thestory.persistence.PersistenceSubscriber
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PlayerModule {

    @Provides @Singleton
    fun service(
            persistenceSubscriber: PersistenceSubscriber
    ): PlayerFacade = PlayerInjectorHandle.service(persistenceSubscriber)
}