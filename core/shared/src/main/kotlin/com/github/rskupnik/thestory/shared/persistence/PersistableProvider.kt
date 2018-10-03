package com.github.rskupnik.thestory.shared.persistence

/**
 * Represents classes that are able to provide a [PersistableState].
 *
 * TODO: Is this actually needed?
 */
interface PersistableProvider {

    val persistableKey: String

    fun getPersistableState(): List<PersistableState>

    // TODO: This should probably be pulled out to a `PersistableConsumer` or something like that
    fun loadPersistableState(state: List<Map<String, Any>>)
}