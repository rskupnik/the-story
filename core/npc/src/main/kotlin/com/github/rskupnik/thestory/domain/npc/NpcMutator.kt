package com.github.rskupnik.thestory.domain.npc

import com.github.rskupnik.thestory.domain.LocationId
import com.github.rskupnik.thestory.domain.npc.internal.NpcInstance
import com.github.rskupnik.thestory.shared.Mutator
import com.github.rskupnik.thestory.shared.MutatorEntry

class NpcMutator private constructor(
        location: LocationId?
) : Mutator() {

    companion object {
        fun new(): Builder = Builder()
    }

    private val locationMutator: MutatorEntry<LocationId> = set(location)

    internal fun mutate(instance: NpcInstance) {
        mutateValue(locationMutator, instance.location)
    }

    data class Builder internal constructor(
            var location: LocationId? = null
    ) {
        fun location(location: LocationId) = apply { this.location = location }
        fun build() = NpcMutator(location)
    }
}