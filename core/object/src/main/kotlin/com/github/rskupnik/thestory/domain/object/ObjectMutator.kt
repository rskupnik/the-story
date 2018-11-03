package com.github.rskupnik.thestory.domain.`object`

import com.github.rskupnik.thestory.domain.`object`.internal.ObjectInstance
import com.github.rskupnik.thestory.shared.Mutator
import com.github.rskupnik.thestory.shared.MutatorEntry

class ObjectMutator private constructor(
        externalState: Map<String, Any>?
) : Mutator() {

    companion object {
        fun new(): Builder = Builder()
    }

    private val externalStateMutator: MutatorEntry<Map<String, Any>> = set(externalState)

    internal fun mutate(obj: ObjectInstance) {
        if (externalStateMutator.enabled)
            obj.externalState = mutateState(obj.externalState, externalStateMutator.value ?: return)
    }

    data class Builder(
            var externalState: Map<String, Any>? = null
    ) {
        fun externalState(externalState: Map<String, Any>) = apply { this.externalState = externalState }
        fun build() = ObjectMutator(externalState)
    }
}