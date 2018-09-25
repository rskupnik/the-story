package com.github.rskupnik.thestory.domain.item.internal

import com.github.rskupnik.thestory.domain.shared.BlueprintInstance
import com.github.rskupnik.thestory.domain.shared.Reference
import java.util.*

internal class ItemInstance(
        override val id: String,
        override val blueprint: ItemBlueprint
) : BlueprintInstance<ItemBlueprint> {

    companion object {
        internal fun restore(id: String, blueprint: ItemBlueprint, currentImageReference: Reference): ItemInstance {
            val instance = ItemInstance(id, blueprint)
            instance.currentImageReference = currentImageReference
            return instance
        }
    }

    internal constructor(blueprint: ItemBlueprint) : this(UUID.randomUUID().toString(), blueprint)

    internal var currentImageReference: Reference? = null
}