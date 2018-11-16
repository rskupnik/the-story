package com.github.rskupnik.thestory.application.internal

import com.github.rskupnik.thestory.application.delegates.ServiceDelegate
import com.github.rskupnik.thestory.shared.Service
import kotlin.reflect.KClass

class Internals internal constructor() {
    private val implementations: MutableMap<Class<out Service>, Any> = HashMap()

    public fun <T : Service> substitute(clazz: Class<out T>, value: Any) {
        implementations[clazz] = value
    }

    internal fun <T : Service> getOrCreate(clazz: KClass<out Service>, creator: () -> T): T {
        val impl: Any? = implementations[clazz.java]
        return if (impl == null) {
            implementations[clazz.java] = ServiceDelegate(creator())
            (implementations[clazz.java]!! as ServiceDelegate<T>).target
        } else impl as T
    }
}