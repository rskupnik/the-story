package com.github.rskupnik.thestory.domain.npc.internal

import com.github.rskupnik.thestory.domain.npc.NpcMutator
import com.github.rskupnik.thestory.domain.npc.NpcService
import com.github.rskupnik.thestory.domain.npc.NpcView
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.json.JsonParser

internal class NpcServiceImplementation(
        private val fileLoader: FileLoader,
        private val jsonParser: JsonParser,
        private val blueprintRepository: NpcBlueprintRepository,
        private val instanceRepository: NpcInstanceRepository
) : NpcService {

    companion object {
        const val DEFINITION_PATH = "modules/unpacked/%s/definitions/npcs.json"
    }

    override fun loadBlueprints(moduleReference: Reference) {
        val definition = fileLoader.getFileHandle(String.format(DEFINITION_PATH, moduleReference.value))
                ?.let { fileLoader.loadAsString(it) } ?: return

        val blueprints = jsonParser.parseList(NpcJson::class.java, definition)
                .asSequence()
                .map { it.toBlueprint() }.toList()

        blueprintRepository.save(blueprints)
    }

    override fun instantiate(reference: Reference): Reference? {
        return instanceRepository.save(NpcInstance(blueprintRepository.find(reference) ?: return null))
    }

    override fun mutate(reference: Reference, mutator: NpcMutator): Boolean {
        mutator.mutate(instanceRepository.find(reference) ?: return false)
        return true
    }

    override fun getNpcView(reference: Reference): NpcView? =
            instanceRepository.find(reference)
            ?.let { NpcView.fromInstance(it) }
}