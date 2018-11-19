package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.application.delegates.BackgroundServiceDelegate
import com.github.rskupnik.thestory.application.internal.Internals
import com.github.rskupnik.thestory.background.BackgroundInjectorHandle
import com.github.rskupnik.thestory.background.BackgroundService
import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.external.feedback.CallbackReceiver
import com.github.rskupnik.thestory.persistence.PersistenceSubscriber
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class BackgroundModule {

    @Provides @Singleton
    fun service(
            internals: Internals,
            moduleService: ModuleService,
            callbackReceiver: CallbackReceiver,
            persistenceSubscriber: PersistenceSubscriber
    ): BackgroundService {
        var delegate = internals.get(BackgroundService::class)
        if (delegate == null) {
            delegate = BackgroundServiceDelegate(BackgroundInjectorHandle.service(moduleService, callbackReceiver, persistenceSubscriber))
            internals.put(BackgroundService::class, delegate)
        }

        return delegate as BackgroundService
    }
}