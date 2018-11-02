package com.github.rskupnik.thestory.background

import com.github.rskupnik.thestory.background.internal.DefaultBackgroundService
import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.gameState.GameStateService

object BackgroundInjectorHandle {
    fun service(
            gameStateService: GameStateService,
            moduleService: ModuleService,
            callbackReceiver: CallbackReceiver
    ): BackgroundService = DefaultBackgroundService(
            gameStateService, moduleService, callbackReceiver
    )
}