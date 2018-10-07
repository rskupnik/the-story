package com.github.rskupnik.thestory.domain.npc.internal

import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.repository.InMemoryRepository

internal class NpcInstanceRepository : InMemoryRepository<NpcInstance> {
    override val storage: MutableMap<Reference, NpcInstance> = HashMap()
}