package com.github.rskupnik.thestory.application.internal

import com.github.rskupnik.thestory.application.delegates.ServiceDelegate
import com.github.rskupnik.thestory.shared.Service
import kotlin.reflect.KClass

class Internals internal constructor() {
    private val implementations: MutableMap<Class<out Service>, Service> = HashMap()

    public fun <T : Service> substitute(clazz: Class<out T>, value: T) {
        (implementations[clazz] as ServiceDelegate<T>).target = value
    }

    internal fun <T : Service> getOrCreate(clazz: KClass<out T>, creator: () -> T): T {
        val impl: Any? = implementations[clazz.java]
        return if (impl == null) {
            implementations[clazz.java] = ServiceDelegate<T>(creator()) as T
            return implementations[clazz.java]!! as T
        } else (impl as ServiceDelegate<T>).self
    }
}