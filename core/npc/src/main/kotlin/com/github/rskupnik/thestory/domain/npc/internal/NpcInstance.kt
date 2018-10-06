package com.github.rskupnik.thestory.domain.npc.internal

import com.github.rskupnik.thestory.domain.LocationId
import com.github.rskupnik.thestory.shared.BlueprintInstance
import java.util.*

internal class NpcInstance(
        override val id: String,
        override val blueprint: NpcBlueprint
) : BlueprintInstance<NpcBlueprint> {

    internal constructor(blueprint: NpcBlueprint) : this(UUID.randomUUID().toString(), blueprint)

    internal var location: LocationId? = null

    companion object {
        fun restore(id: String, blueprint: NpcBlueprint, locationId: LocationId): NpcInstance {
            val instance = NpcInstance(id, blueprint)
            instance.location = locationId
            return instance
        }
    }
}