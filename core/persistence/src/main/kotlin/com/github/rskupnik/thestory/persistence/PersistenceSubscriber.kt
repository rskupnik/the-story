package com.github.rskupnik.thestory.persistence

interface PersistenceSubscriber {
    fun subscribe(persister: Persister)
}