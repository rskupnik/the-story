package com.github.rskupnik.thestory.domain.item

import com.github.rskupnik.thestory.domain.item.internal.ItemInstance
import com.github.rskupnik.thestory.shared.Reference

data class ItemView(
        private val id: Reference,
        private val blueprintId: Reference,
        private val label: String
        //private val image: Image
        // ExternalState
        // ItemPlacement
) {
    companion object {
        internal fun fromInstance(instance: ItemInstance): ItemView = ItemView(
                instance.reference,
                instance.blueprint.reference,
                instance.blueprint.label
                // image
                // external state
                // placement
        )
    }
}