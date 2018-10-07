package com.github.rskupnik.thestory.domain.npc.internal

import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.repository.InMemoryRepository

internal class NpcBlueprintRepository : InMemoryRepository<NpcBlueprint> {
    override val storage: MutableMap<Reference, NpcBlueprint> = HashMap()
}