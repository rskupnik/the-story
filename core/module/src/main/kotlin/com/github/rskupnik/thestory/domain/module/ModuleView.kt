package com.github.rskupnik.thestory.domain.module

import com.github.rskupnik.thestory.domain.module.internal.Module

data class ModuleView(
        val id: String,
        val type: ModuleType
) {
    companion object {
        internal fun fromModule(module: Module): ModuleView =
                ModuleView(module.definition.id, module.definition.type)
    }
}