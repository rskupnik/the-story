package com.github.rskupnik.thestory.core.callback.domain

import com.github.rskupnik.thestory.action.domain.ActionJson
import com.github.rskupnik.thestory.shared.json.DefinitionJsonRepresentation

data class CallbackJson(
        val id: String,
        val actions: List<ActionJson>
) : DefinitionJsonRepresentation<Callback> {
    override fun toDefinition(): Callback = Callback(
            CallbackId.valueOf(id.toUpperCase()),
            DefinitionJsonRepresentation.convertList(actions)
    )
}