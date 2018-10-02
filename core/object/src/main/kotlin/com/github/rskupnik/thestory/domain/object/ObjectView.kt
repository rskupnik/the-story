package com.github.rskupnik.thestory.domain.`object`

import com.github.rskupnik.thestory.domain.`object`.internal.ObjectInstance
import com.github.rskupnik.thestory.shared.ExternalState
import com.github.rskupnik.thestory.shared.Reference

data class ObjectView(
        val id: Reference,
        val blueprintId: Reference,
        val externalState: ExternalState
) {
    companion object {
        internal fun fromInstance(instance: ObjectInstance): ObjectView = ObjectView(
                instance.reference,
                instance.blueprint.reference,
                ExternalState.fromExistingState(instance.externalState)
        )
    }
}