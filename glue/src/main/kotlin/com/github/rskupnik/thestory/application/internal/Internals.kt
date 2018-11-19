package com.github.rskupnik.thestory.application.internal

import com.github.rskupnik.thestory.application.delegates.RuntimeDelegate
import com.github.rskupnik.thestory.shared.Service
import kotlin.reflect.KClass

class Internals internal constructor() {
    private val implementations: MutableMap<Class<out Service>, RuntimeDelegate<out Service>> = HashMap()

    public fun <T : Service> substitute(clazz: Class<out T>, value: T) {
        val delegate: RuntimeDelegate<T> = (implementations[clazz] as RuntimeDelegate<T>) ?: return
        delegate.set(value)
    }

    public fun <T : Service> retrieve(clazz: Class<out T>): T? = implementations[clazz]?.get() as T

    internal fun <T : Service, R : RuntimeDelegate<T>> get(clazz: KClass<out T>): R? = implementations[clazz.java] as R?

    internal fun <S : Service, D : RuntimeDelegate<S>> getOrCreateDelegate(clazz: KClass<S>, creator: () -> D): S {
        var delegate = get(clazz)
        if (delegate == null) {
            delegate = creator.invoke()
            put(clazz, delegate)
        }

        return delegate as S
    }

    private fun <T : Service, R : RuntimeDelegate<T>> put(clazz: KClass<out T>, delegate: R) {
        implementations[clazz.java] = delegate
    }
}