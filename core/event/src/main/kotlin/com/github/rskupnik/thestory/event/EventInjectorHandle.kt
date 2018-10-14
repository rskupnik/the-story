package com.github.rskupnik.thestory.event

import com.github.rskupnik.thestory.event.internal.DefaultEventDispatcher

object EventInjectorHandle {
    fun dispatcher(): EventDispatcher = DefaultEventDispatcher()
}