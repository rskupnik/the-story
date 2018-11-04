package com.github.rskupnik.thestory.domain.`object`.internal

import com.github.rskupnik.thestory.persistence.Persistable
import com.github.rskupnik.thestory.shared.BlueprintInstance
import com.github.rskupnik.thestory.shared.ExternalState

internal class ObjectInstance(
        override val id: String,
        override val blueprint: ObjectBlueprint
) : BlueprintInstance<ObjectBlueprint>, Persistable {

    internal var externalState: ExternalState = ExternalState()

    override fun toPersistableState(): Map<String, Any?> = mapOf(
            "id" to id,
            "blueprint" to blueprint.id,
            "externalState" to ExternalState.fromExistingState(externalState)
    )

    companion object {
        internal fun restore(id: String, blueprint: ObjectBlueprint, externalState: ExternalState): ObjectInstance {
            val instance = ObjectInstance(id, blueprint)
            instance.externalState = externalState
            return instance
        }
    }
}