package com.github.rskupnik.thestory.domain.module.internal

import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.domain.module.ModuleType
import com.github.rskupnik.thestory.domain.module.ModuleView
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.external.asset.AssetLoader
import com.github.rskupnik.thestory.shared.external.file.FileLoader
import com.github.rskupnik.thestory.shared.external.asset.Image
import com.github.rskupnik.thestory.shared.external.asset.ImageProvider
import com.github.rskupnik.thestory.shared.json.JsonParser

internal class ModuleServiceImplementation(
        private val fileLoader: FileLoader,
        private val assetLoader: AssetLoader,
        private val jsonParser: JsonParser,
        private val repository: ModuleRepository
) : ModuleService {

    companion object {
        const val DEFINITION_PATH = "modules/unpacked/%s/module.json"
        const val IMAGE_ATLAS_PATH = "modules/unpacked/%s/assets.atlas"
        const val GFX_IMAGE_PATH = "modules/unpacked/%s/gfx/%s"
    }

    /*override fun load(moduleId: String): List<Reference> {
        val module: Module = parseModule(moduleId)
        if (module.definition.type != ModuleType.STANDALONE) {
            throw IllegalArgumentException("Cannot load module $moduleId because it's not a STANDALONE module")
        }

        repository.save(module)

        val output: MutableList<Reference> = mutableListOf(module.reference)
        output.addAll(loadDependencies(module.definition.dependencies) { IllegalStateException("Error loading required dependency modules") })
        output.addAll(loadDependencies(module.definition.optionalDependencies, null))
        return output.toList()
    }*/

    override fun load(moduleId: String): List<Reference> = parseModule(moduleId)
            .let {
                if (it.definition.type != ModuleType.STANDALONE) {
                    throw IllegalArgumentException("Cannot load module $moduleId because it's not a STANDALONE module")
                } else it
            }
            .also { repository.save(it) }
            .let {
                val output: MutableList<Reference> = mutableListOf(it.reference)
                output.addAll(loadDependencies(it.definition.dependencies) { IllegalStateException("Error loading required dependency modules") })
                output.addAll(loadDependencies(it.definition.optionalDependencies, null))
                return output.toList()
            }

    // TODO: Fail on invalid state (not loaded yet)
    override fun getLoadedModules(): List<ModuleView> = repository.fetchAll().map { ModuleView.fromModule(it) }

    override fun getLoadedStandaloneModule(): ModuleView? =
            getLoadedModules().first { it.type == ModuleType.STANDALONE }

    override fun getImage(ref: Reference): Image? = findImageInProvider(ref) ?: findImageInGfx(ref)

    /**
     * Parse a single [Module] identified by [moduleId].
     */
    private fun parseModule(moduleId: String): Module {
        // Get a handle to the module
        val fileHandle = fileLoader.getFileHandle(DEFINITION_PATH.format(moduleId)) ?: throw IllegalArgumentException("Module $moduleId not found")

        val definition = fileLoader.loadAsString(fileHandle)                  // Load definition as json string
                .let { jsonParser.parse(ModuleJson::class.java, it) }         // Parse JSON to object
                ?.toDefinition() ?: throw IllegalStateException("Error parsing module $moduleId")

        return Module(definition, loadImageProvider(moduleId))
    }

    private fun loadImageProvider(moduleId: String): ImageProvider? {
        return assetLoader.loadImageProvider(fileLoader.getFileHandle(IMAGE_ATLAS_PATH.format(moduleId)) ?: return null)
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

    private fun findImageInProvider(reference: Reference): Image? =
            repository.fetchAll()
                    .asSequence()
                    .mapNotNull { it.imageProvider }
                    .first { it.getImage(reference.value) != null }
                    .getImage(reference.value)

    private fun findImageInGfx(reference: Reference): Image? =
            repository.fetchAll()
                    .asSequence()
                    .mapNotNull { fileLoader.getFileHandle(GFX_IMAGE_PATH.format(it.definition.id, reference.value)) }
                    .first()
                    .let { fileLoader.loadAsImage(it) }
}