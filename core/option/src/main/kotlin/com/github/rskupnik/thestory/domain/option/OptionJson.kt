package com.github.rskupnik.thestory.domain.option

import com.github.rskupnik.thestory.shared.json.DefinitionJsonRepresentation

data class OptionJson(
        val id: String,
        // actions
        val conditions: Map<String, Any>,
        val contexts: List<String>
) : DefinitionJsonRepresentation<Option> {
    override fun toDefinition(): Option =
            Option(
                    id,
                    // TODO: actions
                    conditions,
                    contexts
            )
}