package com.github.rskupnik.thestory.domain.item.internal

import com.github.rskupnik.thestory.domain.item.ItemPlacement
import com.github.rskupnik.thestory.domain.shared.BlueprintInstance
import com.github.rskupnik.thestory.domain.shared.ExternalState
import com.github.rskupnik.thestory.domain.shared.Reference
import com.github.rskupnik.thestory.persistence.Persistable
import java.util.*

internal class ItemInstance(
        override val id: String,
        override val blueprint: ItemBlueprint
) : BlueprintInstance<ItemBlueprint>, Persistable<ItemPersistableState> {

    internal constructor(blueprint: ItemBlueprint) : this(UUID.randomUUID().toString(), blueprint)

    internal var currentImageReference: Reference? = null
    internal var externalState: ExternalState = ExternalState()
    internal var placement: ItemPlacement? = null

    override fun getPersistableState(): ItemPersistableState =
            ItemPersistableState(id, blueprint.id, ExternalState.fromExistingState(externalState), currentImageReference?.value,
                    placement)

    companion object {
        internal fun restore(id: String, blueprint: ItemBlueprint, currentImageReference: Reference? = null,
                             externalState: ExternalState, placement: ItemPlacement? = null): ItemInstance {
            val instance = ItemInstance(id, blueprint)
            instance.currentImageReference = currentImageReference
            instance.externalState = externalState
            instance.placement = placement
            return instance
        }
    }
}