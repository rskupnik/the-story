package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.event.EventDispatcher
import com.github.rskupnik.thestory.event.EventInjectorHandle
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class EventModule {

    @Provides @Singleton
    fun dispatcher(): EventDispatcher = EventInjectorHandle.dispatcher()
}