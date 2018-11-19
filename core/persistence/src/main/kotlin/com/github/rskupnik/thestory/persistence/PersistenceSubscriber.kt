package com.github.rskupnik.thestory.persistence

import com.github.rskupnik.thestory.shared.Service

interface PersistenceSubscriber : Service {
    fun subscribe(persister: Persister)
}