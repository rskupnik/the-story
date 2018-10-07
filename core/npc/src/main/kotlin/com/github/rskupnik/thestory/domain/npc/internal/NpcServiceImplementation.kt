package com.github.rskupnik.thestory.domain.npc.internal

import com.github.rskupnik.thestory.domain.npc.NpcService
import com.github.rskupnik.thestory.domain.npc.NpcView
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.external.file.FileLoader
import com.github.rskupnik.thestory.shared.json.JsonParser
import com.github.rskupnik.thestory.shared.util.CommonFacadeOperations

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
        blueprintRepository.save(
                CommonFacadeOperations.loadBlueprints(moduleReference, fileLoader, DEFINITION_PATH, jsonParser, NpcJson::class)
        )
    }

    override fun instantiate(reference: Reference): Reference? {
        return instanceRepository.save(NpcInstance(blueprintRepository.find(reference) ?: return null))
    }

    override fun getNpcView(reference: Reference): NpcView? =
            instanceRepository.find(reference)
            ?.let { NpcView.fromInstance(it) }
}