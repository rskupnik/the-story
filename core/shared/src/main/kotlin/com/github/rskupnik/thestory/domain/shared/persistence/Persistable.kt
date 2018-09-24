package com.github.rskupnik.thestory.domain.shared.persistence

/**
 * Represents an object that can be persisted.
 *
 * Objects are persisted by providing their respective [PersistableState] to be serialized to JSON.
 */
interface Persistable<T : PersistableState> {
    fun getPersistableState(): T
}