package com.github.rskupnik.thestory.application.internal

import kotlin.reflect.KClass

class Internals internal constructor() {
    private val implementations: MutableMap<Class<out Any>, Any> = HashMap()

    public fun <T : Any> substitute(clazz: Class<out T>, value: T) {
        implementations[clazz] = value
    }

    internal fun <T : Any> getOrCreate(clazz: KClass<out Any>, creator: () -> T): T {
        val impl: Any? = implementations[clazz.java]
        return if (impl == null) {
            implementations[clazz.java] = creator()
            implementations[clazz.java]!! as T
        } else impl as T
    }
}