package com.github.rskupnik.thestory.domain.module.internal

import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.domain.module.ModuleView
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.external.FileLoader
import com.github.rskupnik.thestory.shared.external.Image
import com.github.rskupnik.thestory.shared.external.JsonParser
import com.github.rskupnik.thestory.shared.persistence.PersistableState

internal class ModuleServiceImplementation(
        private val fileLoader: FileLoader,
        //private val assetLoader: AssetLoader
        private val jsonParser: JsonParser
) : ModuleService {

    override val persistableKey: String = "module"

    override fun load(module: String): List<Reference> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLoadedModules(): List<ModuleView> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLoadedStandaloneModule(): ModuleView? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getImage(ref: Reference): Image? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPersistableState(): List<PersistableState> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadPersistableState(state: List<Map<String, Any>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}