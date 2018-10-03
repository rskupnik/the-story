package com.github.rskupnik.thestory.domain.`object`.internal

import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.repository.InMemoryRepository

internal class ObjectInstanceRepository : InMemoryRepository<ObjectInstance> {
    override val storage: MutableMap<Reference, ObjectInstance> = HashMap()
}