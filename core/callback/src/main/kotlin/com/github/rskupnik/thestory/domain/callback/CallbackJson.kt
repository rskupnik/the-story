package com.github.rskupnik.thestory.domain.callback

import com.github.rskupnik.thestory.domain.action.ActionJson
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