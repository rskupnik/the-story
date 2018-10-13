package com.github.rskupnik.thestory.domain.npc.internal

import com.github.rskupnik.thestory.option.domain.OptionJson
import com.github.rskupnik.thestory.shared.json.BlueprintJsonRepresentation
import com.github.rskupnik.thestory.shared.json.DefinitionJsonRepresentation

internal data class NpcJson(
        val id: String,
        val options: List<OptionJson>
) : BlueprintJsonRepresentation<NpcBlueprint> {
    override fun toBlueprint(): NpcBlueprint = NpcBlueprint(
            id,
            DefinitionJsonRepresentation.convertList(options)
    )
}