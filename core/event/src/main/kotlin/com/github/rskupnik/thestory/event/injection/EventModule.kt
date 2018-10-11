package com.github.rskupnik.thestory.event.injection

import com.github.rskupnik.thestory.event.EventDispatcher
import com.github.rskupnik.thestory.event.internal.DefaultEventDispatcher
import dagger.Module

@Module
class EventModule {

    fun dispatcher(): EventDispatcher = DefaultEventDispatcher()
}