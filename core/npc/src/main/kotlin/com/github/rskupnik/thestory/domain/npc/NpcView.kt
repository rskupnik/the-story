package com.github.rskupnik.thestory.domain.npc

import com.github.rskupnik.thestory.domain.LocationId
import com.github.rskupnik.thestory.domain.npc.internal.NpcInstance
import com.github.rskupnik.thestory.shared.Reference

data class NpcView(
        val id: Reference,
        val blueprintId: Reference,
        val location: LocationId?
) {
    companion object {
        internal fun fromInstance(instance: NpcInstance): NpcView = NpcView(
                instance.reference,
                instance.blueprint.reference,
                instance.location
        )
    }
}