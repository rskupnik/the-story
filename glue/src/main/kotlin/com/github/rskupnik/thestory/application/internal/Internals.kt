package com.github.rskupnik.thestory.application.internal

import com.github.rskupnik.thestory.application.delegates.ServiceDelegate
import com.github.rskupnik.thestory.shared.Service
import kotlin.reflect.KClass

class Internals internal constructor() {
    private val implementations: MutableMap<Class<out Service>, ServiceDelegate<out Service>> = HashMap()

    public fun <T : Service> substitute(clazz: Class<out T>, value: T) {
        val delegate: ServiceDelegate<T> = (implementations[clazz] as ServiceDelegate<T>) ?: return
        delegate.target = value
    }

    public fun <T : Service> retrieve(clazz: Class<out T>): T? = implementations[clazz]?.target as T

    internal fun <T : Service, R : ServiceDelegate<T>> get(clazz: KClass<out T>): R? = implementations[clazz.java] as R?

    internal fun <T : Service, R : ServiceDelegate<T>> put(clazz: KClass<out T>, delegate: R) {
        implementations[clazz.java] = delegate
    }
}