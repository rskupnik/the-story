package com.github.rskupnik.thestory.domain.item

import com.github.rskupnik.thestory.domain.item.internal.ItemInstance
import com.github.rskupnik.thestory.shared.Mutator
import com.github.rskupnik.thestory.shared.MutatorEntry
import com.github.rskupnik.thestory.shared.Reference

class ItemMutator private constructor(
        currentImage: Reference?,
        externalState: Map<String, Any>?
) : Mutator() {

    private val currentImageMutator: MutatorEntry<Reference> = set(currentImage)
    private val externalStateMutator: MutatorEntry<Map<String, Any>> = set(externalState)

    internal fun mutate(item: ItemInstance) {
        item.currentImageReference = if (currentImageMutator.enabled) currentImageMutator.value else item.currentImageReference
        if (externalStateMutator.enabled)
            item.externalState = mutateState(item.externalState, externalStateMutator.value ?: return)
    }

    data class Builder(
            var currentImage: Reference? = null,
            var externalState: Map<String, Any>? = null
    ) {
        fun currentImage(currentImage: Reference) = apply { this.currentImage = currentImage }
        fun externalState(externalState: Map<String, Any>) = apply { this.externalState = externalState }
        fun build() = ItemMutator(currentImage, externalState)
    }
}