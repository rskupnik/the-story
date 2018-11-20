package com.github.rskupnik.thestory.api.init

import com.github.rskupnik.thestory.persistence.init.PersistenceInitializer
import com.github.rskupnik.thestory.shared.ServiceInitializer

class Initializer(
        private val initializers: Array<ServiceInitializer>
) {

    fun init() {
        initializers.forEach { it.init() }
    }
}