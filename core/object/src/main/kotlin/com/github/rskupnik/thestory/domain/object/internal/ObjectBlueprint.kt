package com.github.rskupnik.thestory.domain.`object`.internal

import com.github.rskupnik.thestory.option.domain.Option
import com.github.rskupnik.thestory.shared.Blueprint
import com.github.rskupnik.thestory.shared.Reference

internal data class ObjectBlueprint(
        val id: String,
        val initialState: Map<String, Any>,
        val options: List<Option>
) : Blueprint {
    override val reference: Reference = Reference.to(id)
}