package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.background.BackgroundInjectorHandle
import com.github.rskupnik.thestory.background.BackgroundService
import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class BackgroundModule {

    @Provides @Singleton
    fun service(
            moduleService: ModuleService,
            callbackReceiver: CallbackReceiver
    ): BackgroundService = BackgroundInjectorHandle.service(
            moduleService, callbackReceiver
    )
}