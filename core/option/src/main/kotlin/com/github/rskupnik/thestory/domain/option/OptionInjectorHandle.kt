package com.github.rskupnik.thestory.domain.option

import com.github.rskupnik.thestory.domain.option.internal.OptionServiceImplementation
import com.github.rskupnik.thestory.event.EventDispatcher

object OptionInjectorHandle {
    fun service(eventDispatcher: EventDispatcher): OptionService = OptionServiceImplementation(eventDispatcher)
}