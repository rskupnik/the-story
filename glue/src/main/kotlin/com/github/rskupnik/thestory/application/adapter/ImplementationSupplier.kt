package com.github.rskupnik.thestory.application.adapter

import com.github.rskupnik.thestory.shared.external.Port
import kotlin.reflect.KClass

interface ImplementationSupplier {

    fun <T: Port> getImplementation(klass: KClass<T>): T
}