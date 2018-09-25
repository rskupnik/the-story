package com.github.rskupnik.thestory.domain.item.internal

import com.github.rskupnik.thestory.domain.shared.Reference
import com.github.rskupnik.thestory.domain.shared.repository.InMemoryRepository

internal class ItemInstanceRepositoryK : InMemoryRepository<ItemInstance> {
    override val storage: MutableMap<Reference, ItemInstance> = HashMap()
}