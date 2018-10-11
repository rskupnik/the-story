package com.github.rskupnik.thestory.event

import kotlin.reflect.KClass

interface EventDispatcher {

    fun dispatch(event: Event)

    fun register(klass: KClass<out Event>, handler: (Event) -> Unit)
}