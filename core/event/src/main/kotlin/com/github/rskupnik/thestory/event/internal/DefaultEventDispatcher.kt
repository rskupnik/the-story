package com.github.rskupnik.thestory.event.internal

import com.github.rskupnik.thestory.event.Event
import com.github.rskupnik.thestory.event.EventDispatcher
import kotlin.reflect.KClass

internal class DefaultEventDispatcher : EventDispatcher {

    private val handlers: MutableMap<KClass<out Event>, MutableList<(Event) -> Unit>> = HashMap()

    override fun dispatch(event: Event) {
        for (handler in getOrCreate(event::class)) {
            handler.invoke(event)
        }
    }

    override fun register(klass: KClass<out Event>, handler: (Event) -> Unit) {
        getOrCreate(klass).add(handler)
    }

    private fun getOrCreate(klass: KClass<out Event>): MutableList<(Event) -> Unit> {
        return if (handlers[klass] != null) handlers[klass]!!
        else {
            val list: MutableList<(Event) -> Unit> = mutableListOf()
            handlers[klass] = list
            list
        }
    }
}