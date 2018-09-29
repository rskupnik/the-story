package com.github.rskupnik.thestory.domain.module.internal

import com.github.rskupnik.thestory.domain.module.ModuleType
import com.github.rskupnik.thestory.shared.Definition

internal data class ModuleDefinition(
        val id: String,
        val type: ModuleType,
        val dependencies: List<String>,
        val optionalDependencies: List<String>
) : Definition