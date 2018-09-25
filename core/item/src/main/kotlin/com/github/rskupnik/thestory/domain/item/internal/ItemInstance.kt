package com.github.rskupnik.thestory.domain.item.internal

import com.github.rskupnik.thestory.domain.shared.BlueprintInstance
import com.github.rskupnik.thestory.domain.shared.ExternalState
import com.github.rskupnik.thestory.domain.shared.Reference
import com.github.rskupnik.thestory.domain.shared.persistence.Persistable
import java.util.*

internal class ItemInstance(
        override val id: String,
        override val blueprint: ItemBlueprint
) : BlueprintInstance<ItemBlueprint>, Persistable<ItemPersistableState> {

    internal constructor(blueprint: ItemBlueprint) : this(UUID.randomUUID().toString(), blueprint)

    internal var currentImageReference: Reference? = null
    internal var externalState: ExternalState = ExternalState()

    override fun getPersistableState(): ItemPersistableState =
            ItemPersistableState(id, blueprint.id, ExternalState.fromExistingState(externalState), currentImageReference?.value)

    companion object {
        internal fun restore(id: String, blueprint: ItemBlueprint, currentImageReference: Reference?,
                             externalState: ExternalState): ItemInstance {
            val instance = ItemInstance(id, blueprint)
            instance.currentImageReference = currentImageReference
            instance.externalState = externalState
            return instance
        }
    }
}