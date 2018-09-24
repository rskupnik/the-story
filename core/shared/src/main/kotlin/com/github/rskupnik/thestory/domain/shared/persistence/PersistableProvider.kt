package com.github.rskupnik.thestory.domain.shared.persistence

/**
 * Represents classes that are able to provide a [PersistableState].
 *
 * TODO: Is this actually needed?
 */
interface PersistableProvider<R : PersistableState> {

    val persistableKey: String

    fun getPersistableState(): List<R>

    // TODO: This should probably be pulled out to a `PersistableConsumer` or something like that
    fun loadPersistableState(state: List<Map<String, Any>>)
}