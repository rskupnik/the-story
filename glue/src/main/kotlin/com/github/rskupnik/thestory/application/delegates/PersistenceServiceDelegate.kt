package com.github.rskupnik.thestory.application.delegates

import com.github.rskupnik.thestory.persistence.PersistenceService

internal class PersistenceServiceDelegate(
        override var delegatee: PersistenceService
) : RuntimeDelegate<PersistenceService>(), PersistenceService {

    override fun save() = get().save()

    override fun readState(filename: String): Map<String, Any?> = get().readState(filename)

    override fun loadState(state: Map<String, Any?>) = get().loadState(state)

}