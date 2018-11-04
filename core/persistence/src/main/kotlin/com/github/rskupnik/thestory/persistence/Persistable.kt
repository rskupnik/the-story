package com.github.rskupnik.thestory.persistence

/**
 * Marks an object as being able to be persisted.
 *
 * Must produce a [PersistableState] that represents the state of the object to be persisted.
 */
interface Persistable<T : PersistableState> {
    fun toPersistableState(): T
}