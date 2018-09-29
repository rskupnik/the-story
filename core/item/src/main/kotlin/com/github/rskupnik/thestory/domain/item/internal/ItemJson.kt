package com.github.rskupnik.thestory.domain.item.internal

import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.json.BlueprintJsonRepresentation

internal data class ItemJson(
        val id: String,
        val label: String,
        val image: String,
        val type: String?,
        val initialState: Map<String, Any>
        // TODO options
        // TODO callbacks
) : BlueprintJsonRepresentation<ItemBlueprint> {
    override fun toBlueprint(): ItemBlueprint = ItemBlueprint(
            id,
            label,
            Reference.to(image),
            if (type != null) ItemBlueprint.Type.valueOf(type) else ItemBlueprint.Type.REGULAR,
            initialState
    )
}