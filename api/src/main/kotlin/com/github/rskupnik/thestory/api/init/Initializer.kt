package com.github.rskupnik.thestory.api.init

import com.github.rskupnik.thestory.persistence.init.PersistenceInitializer
import com.github.rskupnik.thestory.shared.ServiceInitializer

/**
 * This class is used when starting the application to allow the core code to do some initializing.
 *
 * The application layer that provides dependency injection should inject initializers into this class that it wants
 * to initialize at the start.
 */
class Initializer(
        private val initializers: Array<ServiceInitializer>
) {

    fun init() {
        initializers.forEach { it.init() }
    }
}