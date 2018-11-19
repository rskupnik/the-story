package com.github.rskupnik.thestory.application.internal

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class InternalModule {

    @Provides @Singleton
    fun internals(): Internals = Internals()
}