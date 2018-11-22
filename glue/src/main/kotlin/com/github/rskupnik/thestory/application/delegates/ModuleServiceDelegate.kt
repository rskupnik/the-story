package com.github.rskupnik.thestory.application.delegates

import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.domain.module.ModuleView
import com.github.rskupnik.thestory.external.asset.Image
import com.github.rskupnik.thestory.shared.Reference

internal class ModuleServiceDelegate(
        override var delegatee: ModuleService
) : RuntimeDelegate<ModuleService>(), ModuleService {
    override fun load(moduleId: String): List<Reference> = get().load(moduleId)

    override fun getLoadedModules(): List<ModuleView> = get().getLoadedModules()

    override fun getLoadedStandaloneModule(): ModuleView? = get().getLoadedStandaloneModule()

    override fun getImage(ref: Reference): Image? = get().getImage(ref)
}