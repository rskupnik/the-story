package com.github.rskupnik.thestory.domain.option.injection

import com.github.rskupnik.thestory.domain.option.OptionService
import com.github.rskupnik.thestory.domain.option.internal.OptionServiceImplementation
import com.github.rskupnik.thestory.event.EventDispatcher
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class OptionModule {

    @Provides @Singleton
    fun service(eventDispatcher: EventDispatcher): OptionService = OptionServiceImplementation(eventDispatcher)
}