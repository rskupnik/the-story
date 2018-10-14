package com.github.rskupnik.thestory.application.modules

import com.github.rskupnik.thestory.domain.option.OptionInjectorHandle
import com.github.rskupnik.thestory.domain.option.OptionService
import com.github.rskupnik.thestory.event.EventDispatcher
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class OptionModule {

    @Provides @Singleton
    fun service(eventDispatcher: EventDispatcher): OptionService = OptionInjectorHandle.service(eventDispatcher)
}