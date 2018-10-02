package com.github.rskupnik.thestory.domain.`object`.internal

import com.github.rskupnik.thestory.domain.option.OptionJson
import com.github.rskupnik.thestory.shared.json.BlueprintJsonRepresentation
import com.github.rskupnik.thestory.shared.json.DefinitionJsonRepresentation

internal data class ObjectJson(
        val id: String,
        val options: List<OptionJson>,
        val initialState: Map<String, Any>
) : BlueprintJsonRepresentation<ObjectBlueprint> {
    override fun toBlueprint(): ObjectBlueprint = ObjectBlueprint(
            id,
            initialState,
            DefinitionJsonRepresentation.convertList(options)
    )
}