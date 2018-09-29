package com.github.rskupnik.thestory.domain.module.internal

import com.github.rskupnik.thestory.domain.module.ModuleType
import com.github.rskupnik.thestory.shared.json.DefinitionJsonRepresentation

internal data class ModuleJson(
        val id: String,
        val type: String,
        val dependencies: List<String>,
        val optionalDependencies: List<String>
) : DefinitionJsonRepresentation<ModuleDefinition> {
    override fun toDefinition(): ModuleDefinition = ModuleDefinition(
            id,
            ModuleType.valueOf(type),
            dependencies,
            optionalDependencies
    )
}