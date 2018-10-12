package com.github.rskupnik.thestory.option.domain

import com.github.rskupnik.thestory.action.domain.ActionJson
import com.github.rskupnik.thestory.shared.json.DefinitionJsonRepresentation

data class OptionJson(
        val id: String,
        val actions: List<ActionJson>,
        val conditions: Map<String, Any>,
        val contexts: List<String>
) : DefinitionJsonRepresentation<Option> {
    override fun toDefinition(): Option =
            Option(
                    id,
                    DefinitionJsonRepresentation.convertList(actions),
                    conditions,
                    contexts
            )
}