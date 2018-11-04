package com.github.rskupnik.thestory.domain.item.internal

import com.github.rskupnik.thestory.item.domain.ItemPlacement
import com.github.rskupnik.thestory.persistence.Persistable
import com.github.rskupnik.thestory.shared.BlueprintInstance
import com.github.rskupnik.thestory.shared.ExternalState
import com.github.rskupnik.thestory.shared.Reference
import java.util.*

internal class ItemInstance(
        override val id: String,
        override val blueprint: ItemBlueprint
) : BlueprintInstance<ItemBlueprint>, Persistable {

    internal constructor(blueprint: ItemBlueprint) : this(UUID.randomUUID().toString(), blueprint)

    internal var currentImageReference: Reference? = null
    internal var externalState: ExternalState = ExternalState()
    internal var placement: ItemPlacement? = null

    override fun toPersistableState(): Map<String, Any?> = mapOf(
            "id" to id,
            "blueprint" to blueprint.id,
            "externalState" to ExternalState.fromExistingState(externalState),
            "currentImage" to currentImageReference?.value,
            "placement" to placement
    )

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