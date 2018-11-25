package com.github.rskupnik.thestory.event

import kotlin.reflect.KClass

/**
 * Handles sending and retrieving events among the core application.
 */
interface EventDispatcher {

    /**
     * Dispatch an [event].
     */
    fun dispatch(event: Event)

    /**
     * Register a [handler] for a particular [klass] of an event.
     */
    fun register(klass: KClass<out Event>, handler: (Event) -> Unit)
}