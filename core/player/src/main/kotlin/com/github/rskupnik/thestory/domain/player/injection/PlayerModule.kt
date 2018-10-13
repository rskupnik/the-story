package com.github.rskupnik.thestory.domain.player.injection

import com.github.rskupnik.thestory.domain.player.PlayerFacade
import com.github.rskupnik.thestory.domain.player.internal.DefaultPlayerService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PlayerModule {

    @Provides @Singleton
    fun service(): PlayerFacade = DefaultPlayerService()
}