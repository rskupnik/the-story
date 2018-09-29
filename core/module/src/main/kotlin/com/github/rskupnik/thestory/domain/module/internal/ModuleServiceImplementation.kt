package com.github.rskupnik.thestory.domain.module.internal

import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.domain.module.ModuleType
import com.github.rskupnik.thestory.domain.module.ModuleView
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.external.FileLoader
import com.github.rskupnik.thestory.shared.external.Image
import com.github.rskupnik.thestory.shared.external.JsonParser
import com.github.rskupnik.thestory.shared.persistence.PersistableState

internal class ModuleServiceImplementation(
        private val fileLoader: FileLoader,
        //private val assetLoader: AssetLoader
        private val jsonParser: JsonParser,
        private val repository: ModuleRepository
) : ModuleService {

    override val persistableKey: String = "module"

    companion object {
        const val DEFINITION_PATH = "modules/unpacked/%s/module.json"
    }

    override fun load(moduleId: String): List<Reference> {
        val module: Module = parseModule(moduleId)
        if (module.definition.type != ModuleType.STANDALONE) {
            throw IllegalArgumentException("Cannot load module $moduleId because it's not a STANDALONE module")
        }

        repository.save(module)

        val output: MutableList<Reference> = mutableListOf(module.reference)
        output.addAll(loadDependencies(module.definition.dependencies) { IllegalStateException("Error loading required dependency modules") })
        output.addAll(loadDependencies(module.definition.optionalDependencies, null))
        return output.toList()
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

    /**
     * Parse a single [Module] identified by [moduleId].
     */
    private fun parseModule(moduleId: String): Module {
        // Get a handle to the module
        val fileHandle = fileLoader.getFileHandle(DEFINITION_PATH.format(moduleId)) ?: throw IllegalArgumentException("Module $moduleId not found")

        val definition = fileLoader.loadAsString(fileHandle)                  // Load definition as json string
                .let { jsonParser.parse(ModuleJson::class.java, it) }         // Parse JSON to object
                ?.toDefinition() ?: throw IllegalStateException("Error parsing module $moduleId")

        val imageProvider = loadImageProvider(moduleId)

        return Module(definition)
    }

    private fun loadImageProvider(moduleId: String): Any? {
        TODO("not yet implemented")
    }

    private fun loadDependencies(dependencies: List<String>?, onError: (() -> RuntimeException)?): List<Reference> {
        if (dependencies == null) return emptyList()

        return try {
            dependencies
                    .map { parseModule(it) }
                    .also { repository.save(it) }
                    .map { it.reference }
        } catch (e: Exception) {
            return if (onError != null) throw onError() else emptyList()
        }
    }
}