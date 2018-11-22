package com.github.rskupnik.thestory.domain.module

import com.github.rskupnik.thestory.external.asset.Image
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.Service

interface ModuleService : Service {

    fun load(moduleId: String): List<Reference>

    fun getLoadedModules(): List<ModuleView>

    fun getLoadedStandaloneModule(): ModuleView?

    // TODO: Should this be here or in a separate component that deals with assets?
    fun getImage(ref: Reference): Image?
}