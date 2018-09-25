package com.github.rskupnik.thestory.domain.item.internal

import com.github.rskupnik.thestory.domain.shared.Reference
import com.github.rskupnik.thestory.domain.shared.repository.InMemoryRepository

internal class ItemBlueprintRepositoryK : InMemoryRepository<ItemBlueprint> {
    override val storage: MutableMap<Reference, ItemBlueprint> = HashMap()
}