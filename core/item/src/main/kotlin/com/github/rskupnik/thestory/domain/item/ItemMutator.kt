package com.github.rskupnik.thestory.domain.item

import com.github.rskupnik.thestory.domain.item.internal.ItemInstance
import com.github.rskupnik.thestory.item.domain.ItemPlacement
import com.github.rskupnik.thestory.shared.Mutator
import com.github.rskupnik.thestory.shared.MutatorEntry
import com.github.rskupnik.thestory.shared.Reference

class ItemMutator private constructor(
        currentImage: Reference?,
        externalState: Map<String, Any>?,
        placement: ItemPlacement?
) : Mutator() {

    companion object {
        fun new(): Builder = Builder()
    }

    private val currentImageMutator: MutatorEntry<Reference> = set(currentImage)
    private val externalStateMutator: MutatorEntry<Map<String, Any>> = set(externalState)
    private val placementMutator: MutatorEntry<ItemPlacement> = set(placement)

    internal fun mutate(item: ItemInstance) {
        item.currentImageReference = mutateValue(currentImageMutator, item.currentImageReference)
        if (externalStateMutator.enabled)
            item.externalState = mutateState(item.externalState, externalStateMutator.value ?: return)
        item.placement = mutateValue(placementMutator, item.placement)
    }

    data class Builder internal constructor(
            var currentImage: Reference? = null,
            var externalState: Map<String, Any>? = null,
            var placement: ItemPlacement? = null
    ) {
        fun currentImage(currentImage: Reference) = apply { this.currentImage = currentImage }
        fun externalState(externalState: Map<String, Any>) = apply { this.externalState = externalState }
        fun placement(placement: ItemPlacement) = apply { this.placement = placement }
        fun build() = ItemMutator(currentImage, externalState, placement)
    }
}