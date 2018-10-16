package com.github.rskupnik.thestory.option.domain

import com.github.rskupnik.thestory.action.domain.Action
import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.Definition
import com.github.rskupnik.thestory.shared.entity.EntityId
import com.github.rskupnik.thestory.shared.external.dto.OptionLabel

data class Option(
        val id: String,
        val actions: List<Action>,
        val conditions: Map<String, Any>,
        val contexts: List<String>
) : Definition {
    companion object {
        fun filterByContext(input: List<Option>, context: Context): List<Option> =
                input.asSequence()
                        .filter { !it.contexts.isEmpty() }
                        .filter {
                            it.contexts.any { context == Context.fromString(it) }
                        }
                        .toList()

        fun filterByConditions(input: List<Option>, fulfilledConditions: Map<String, Any>): List<Option> =
                input.asSequence()
                        .filter { !it.conditions.isEmpty() }
                        .filter {
                            it.conditions.all {
                                fulfilledConditions[it.key] != null && fulfilledConditions[it.key] == it.value
                            }
                        }
                        .toList()

        fun optionsToLabels(entityId: EntityId, options: List<Option>, context: Context? = null) = options.map { OptionLabel(entityId, it.id, context) }
    }
}