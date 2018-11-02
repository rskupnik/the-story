package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.gameState.GameStateInjectorHandle
import com.github.rskupnik.thestory.gameState.GameStateService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class GameStateModule {

    @Provides @Singleton
    fun service(): GameStateService = GameStateInjectorHandle.service()
}