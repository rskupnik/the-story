package com.github.rskupnik.thestory.persistence

/**
 * Marks an object as being able to be persisted.
 *
 * Must produce a [Map]<[String], [Any]> that represents the state of the object to be persisted.
 */
interface Persistable {
    fun toPersistableState(): Map<String, Any>
}