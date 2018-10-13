package com.github.rskupnik.thestory.application.adapter

import com.github.rskupnik.thestory.shared.external.Port
import kotlin.reflect.KClass

interface ImplementationProvider {

    fun <T : Port> provideImplementation(klass: KClass<T>, implementation: T)
}