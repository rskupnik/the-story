package com.github.rskupnik.thestory.persistence.init

import com.github.rskupnik.thestory.persistence.PersistenceSubscriber
import com.github.rskupnik.thestory.persistence.Persister

object PersistenceInitializerInjectionHandle {

    fun initializer(
            persistenceSubscriber: PersistenceSubscriber,
            persisters: Array<Persister>
    ): PersistenceInitializer = PersistenceInitializer(persistenceSubscriber, persisters)
}