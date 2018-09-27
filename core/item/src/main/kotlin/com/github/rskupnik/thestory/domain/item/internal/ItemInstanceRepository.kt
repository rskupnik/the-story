package com.github.rskupnik.thestory.domain.item.internal

import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.repository.InMemoryRepository

internal class ItemInstanceRepository : InMemoryRepository<ItemInstance> {
    override val storage: MutableMap<Reference, ItemInstance> = HashMap()
}