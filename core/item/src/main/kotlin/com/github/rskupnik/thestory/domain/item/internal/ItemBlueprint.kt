package com.github.rskupnik.thestory.domain.item.internal

import com.github.rskupnik.thestory.domain.shared.Blueprint
import com.github.rskupnik.thestory.domain.shared.Reference

internal data class ItemBlueprint(
        val id: String,
        val label: String,
        val imageReference: Reference,
        val type: Type,
        val initialState: Map<String, Any>
        //val options: List<Option>,
        //val callbacks: List<Callback>
) : Blueprint {
    override val reference: Reference
        get() = Reference(id)

    internal enum class Type {
        REGULAR, WEAPON
    }
}