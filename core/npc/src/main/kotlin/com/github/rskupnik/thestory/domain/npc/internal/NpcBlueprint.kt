package com.github.rskupnik.thestory.domain.npc.internal

import com.github.rskupnik.thestory.domain.option.Option
import com.github.rskupnik.thestory.shared.Blueprint
import com.github.rskupnik.thestory.shared.Reference

internal data class NpcBlueprint(
        val id: String,
        val options: List<Option>
) : Blueprint {
    override val reference: Reference = Reference.to(id)
}