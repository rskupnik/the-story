package com.github.rskupnik.thestory.application.adapter

import com.github.rskupnik.thestory.external.Port
import kotlin.reflect.KClass

interface ImplementationProvider {

    fun <T : Port> provideImplementation(klass: Class<T>, implementation: T)
}