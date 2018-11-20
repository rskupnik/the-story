package com.github.rskupnik.thestory.persistence.init

import com.github.rskupnik.thestory.persistence.PersistenceSubscriber
import com.github.rskupnik.thestory.persistence.Persister
import com.github.rskupnik.thestory.shared.ServiceInitializer

class PersistenceInitializer(
        private val persistenceSubscriber: PersistenceSubscriber,
        private val persisters: Array<Persister>
) : ServiceInitializer {

    override fun init() {
        persisters.forEach { persistenceSubscriber.subscribe(it) }
    }
}