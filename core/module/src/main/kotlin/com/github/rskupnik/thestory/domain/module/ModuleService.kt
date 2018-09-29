package com.github.rskupnik.thestory.domain.module

import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.external.Image
import com.github.rskupnik.thestory.shared.persistence.PersistableProvider
import com.github.rskupnik.thestory.shared.persistence.PersistableState

interface ModuleService : PersistableProvider<PersistableState> {

    fun load(module: String): List<Reference>

    fun getLoadedModules(): List<ModuleView>

    fun getLoadedStandaloneModule(): ModuleView?

    // TODO: Should this be here or in a separate component that deals with assets?
    fun getImage(ref: Reference): Image?
}