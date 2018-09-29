package com.github.rskupnik.thestory.domain.module.internal

import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.repository.InMemoryRepository

internal class ModuleRepository : InMemoryRepository<Module> {
    override val storage: MutableMap<Reference, Module> = HashMap()
}