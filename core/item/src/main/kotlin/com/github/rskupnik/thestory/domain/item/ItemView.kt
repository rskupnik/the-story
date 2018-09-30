package com.github.rskupnik.thestory.domain.item

import com.github.rskupnik.thestory.domain.item.internal.ItemInstance
import com.github.rskupnik.thestory.shared.ExternalState
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.external.asset.Image

data class ItemView(
        val id: Reference,
        val blueprintId: Reference,
        val label: String,
        val image: Image,
        val externalState: ExternalState,
        val placement: ItemPlacement?
) {
    companion object {
        internal fun fromInstance(instance: ItemInstance, image: Image): ItemView = ItemView(
                instance.reference,
                instance.blueprint.reference,
                instance.blueprint.label,
                image,
                ExternalState.fromExistingState(instance.externalState),
                instance.placement
        )
    }
}