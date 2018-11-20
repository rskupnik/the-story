package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.application.delegates.PlayerServiceDelegate
import com.github.rskupnik.thestory.application.internal.Internals
import com.github.rskupnik.thestory.domain.player.PlayerFacade
import com.github.rskupnik.thestory.domain.player.PlayerInjectorHandle
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PlayerModule {

    @Provides @Singleton
    fun service(
            internals: Internals
    ): PlayerFacade = internals.getOrCreateDelegate(PlayerFacade::class) {
        PlayerServiceDelegate(PlayerInjectorHandle.service())
    }
}