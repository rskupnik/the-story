package com.github.rskupnik.thestory.domain.item.internal

import com.github.rskupnik.thestory.core.callback.domain.CallbackJson
import com.github.rskupnik.thestory.option.domain.OptionJson
import com.github.rskupnik.thestory.shared.ExternalState
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.json.BlueprintJsonRepresentation
import com.github.rskupnik.thestory.shared.json.DefinitionJsonRepresentation

internal data class ItemJson(
        val id: String,
        val label: String,
        val image: String,
        val type: String?,
        val initialState: Map<String, Any>?,
        val options: List<OptionJson>?,
        val callbacks: List<CallbackJson>?
) : BlueprintJsonRepresentation<ItemBlueprint> {
    override fun toBlueprint(): ItemBlueprint = ItemBlueprint(
            id,
            label,
            Reference.to(image),
            if (type != null) ItemBlueprint.Type.valueOf(type) else ItemBlueprint.Type.REGULAR,
            ExternalState.fromExistingState(initialState),
            if (options != null) DefinitionJsonRepresentation.convertList(options) else emptyList(),
            if (callbacks != null) DefinitionJsonRepresentation.convertList(callbacks) else emptyList()
    )
}