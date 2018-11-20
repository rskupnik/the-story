package com.github.rskupnik.thestory.background

import com.github.rskupnik.thestory.background.internal.DefaultBackgroundService
import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.persistence.PersistenceSubscriber

object BackgroundInjectorHandle {
    fun service(
            moduleService: ModuleService,
            callbackReceiver: CallbackReceiver
    ): BackgroundService = DefaultBackgroundService(
            moduleService, callbackReceiver
    )
}