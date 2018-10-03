package com.github.rskupnik.thestory.domain.`object`.internal

import com.github.rskupnik.thestory.domain.`object`.ObjectMutator
import com.github.rskupnik.thestory.domain.`object`.ObjectService
import com.github.rskupnik.thestory.domain.`object`.ObjectView
import com.github.rskupnik.thestory.domain.option.Option
import com.github.rskupnik.thestory.shared.ExternalState
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.external.file.FileLoader
import com.github.rskupnik.thestory.shared.json.JsonParser
import com.github.rskupnik.thestory.shared.persistence.PersistableState
import com.github.rskupnik.thestory.shared.util.CommonFacadeOperations

internal class ObjectServiceImplementation(
        private val fileLoader: FileLoader,
        private val jsonParser: JsonParser,
        private val blueprintRepository: ObjectBlueprintRepository,
        private val instanceRepository: ObjectInstanceRepository
) : ObjectService {

    override val persistableKey: String = "objects"

    companion object {
        const val DEFINITION_PATH = "modules/unpacked/%s/definitions/objects.json"
    }

    override fun loadBlueprints(moduleReference: Reference) {
        blueprintRepository.save(
                CommonFacadeOperations.loadBlueprints(moduleReference, fileLoader, DEFINITION_PATH, jsonParser, ObjectJson::class)
        )
    }

    override fun instantiate(id: String, blueprintReference: Reference): Reference? {
        val blueprint = blueprintRepository.find(blueprintReference) ?: return null
        val instance = ObjectInstance(id, blueprint)
        instance.externalState = ExternalState.fromExistingState(blueprint.initialState)
        return instanceRepository.save(instance)
    }

    override fun instantiateUnique(id: String, blueprintReference: Reference): Reference? =
            if (instanceRepository.find(Reference.to(id)) != null) null else instantiate(id, blueprintReference)

    override fun getObjectView(reference: Reference): ObjectView? {
        return ObjectView.fromInstance(instanceRepository.find(reference) ?: return null)
    }

    override fun getOptions(reference: Reference): List<Option> {
        val instance = instanceRepository.find(reference) ?: return emptyList()
        return Option.filterByConditions(instance.blueprint.options, instance.externalState)
    }

    override fun mutate(reference: Reference, mutator: ObjectMutator): Boolean {
        mutator.mutate(instanceRepository.find(reference) ?: return false)
        return true
    }

    override fun getPersistableState(): List<PersistableState> =
            CommonFacadeOperations.getPersistableState(instanceRepository.fetchAll())

    override fun loadPersistableState(state: List<Map<String, Any>>) {
        val loadedState = ObjectPersistableState.fromRawData(state)
        instanceRepository.save(loadedState.mapNotNull { instantiateFromState(it) })
    }

    private fun instantiateFromState(state: ObjectPersistableState): ObjectInstance? {
        val blueprint = blueprintRepository.find(Reference.to(state.blueprint)) ?: return null
        return ObjectInstance.restore(state.id, blueprint, state.externalState)
    }
}