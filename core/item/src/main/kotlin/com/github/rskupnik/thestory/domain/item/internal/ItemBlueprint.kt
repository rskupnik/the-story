package com.github.rskupnik.thestory.domain.item.internal

import com.github.rskupnik.thestory.domain.callback.Callback
import com.github.rskupnik.thestory.domain.option.Option
import com.github.rskupnik.thestory.shared.Blueprint
import com.github.rskupnik.thestory.shared.Reference

internal data class ItemBlueprint(
        val id: String,
        val label: String,
        val imageReference: Reference,
        val type: Type,
        val initialState: Map<String, Any>,
        val options: List<Option>,
        val callbacks: List<Callback>
) : Blueprint {
    override val reference: Reference = Reference.to(id)

    internal enum class Type {
        REGULAR, WEAPON
    }
}