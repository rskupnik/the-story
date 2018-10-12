package com.github.rskupnik.thestory.action.domain

import com.github.rskupnik.thestory.shared.json.DefinitionJsonRepresentation

data class ActionJson(
        val id: String,
        val contexts: List<String>,
        val params: Map<String, Any>,
        val conditions: Map<String, Any>
) : DefinitionJsonRepresentation<Action> {
    override fun toDefinition(): Action = Action(id, contexts, params, conditions)
}