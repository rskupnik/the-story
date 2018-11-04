package com.github.rskupnik.thestory.persistence

/**
 * Marks an object as being able to be persisted.
 *
 * Must produce a [Map]<[String], [Any]?> that represents the state of the object to be persisted.
 */
interface Persistable {
    fun toPersistableState(): Map<String, Any?>

    companion object {
        fun <T : Persistable> instantiate(state: Map<String, Any?>, constructor: (Map<String, Any?>) -> T?): T? = constructor(state)
    }
}