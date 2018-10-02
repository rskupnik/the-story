package com.github.rskupnik.thestory.domain.`object`.internal

import com.github.rskupnik.thestory.shared.BlueprintInstance
import com.github.rskupnik.thestory.shared.ExternalState
import com.github.rskupnik.thestory.shared.persistence.Persistable

internal class ObjectInstance(
        override val id: String,
        override val blueprint: ObjectBlueprint
) : BlueprintInstance<ObjectBlueprint>, Persistable<ObjectPersistableState> {

    internal var externalState: ExternalState = ExternalState()

    override fun toPersistableState(): ObjectPersistableState = ObjectPersistableState(
            id, blueprint.id, ExternalState.fromExistingState(externalState)
    )

    companion object {
        internal fun restore(id: String, blueprint: ObjectBlueprint, externalState: ExternalState): ObjectInstance {
            val instance = ObjectInstance(id, blueprint)
            instance.externalState = externalState
            return instance
        }
    }
}