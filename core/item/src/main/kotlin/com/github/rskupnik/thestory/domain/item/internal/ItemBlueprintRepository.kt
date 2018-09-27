package com.github.rskupnik.thestory.domain.item.internal

import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.repository.InMemoryRepository

internal class ItemBlueprintRepository : InMemoryRepository<ItemBlueprint> {
    override val storage: MutableMap<Reference, ItemBlueprint> = HashMap()
}