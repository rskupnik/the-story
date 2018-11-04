package com.github.rskupnik.thestory.domain.`object`.internal

import com.github.rskupnik.thestory.domain.`object`.ObjectMutator
import com.github.rskupnik.thestory.domain.`object`.ObjectService
import com.github.rskupnik.thestory.domain.`object`.ObjectView
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.option.domain.Option
import com.github.rskupnik.thestory.persistence.Persistable
import com.github.rskupnik.thestory.shared.ExternalState
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.json.JsonParser

typealias State = Map<String, Any?>

internal class ObjectServiceImplementation(
        private val fileLoader: FileLoader,
        private val jsonParser: JsonParser,
        private val blueprintRepository: ObjectBlueprintRepository,
        private val instanceRepository: ObjectInstanceRepository
) : ObjectService {

    override val persistenceKey: String = "objects"

    companion object {
        const val DEFINITION_PATH = "modules/unpacked/%s/definitions/objects.json"
    }

    override fun loadBlueprints(moduleReference: Reference) {
        val definition = fileLoader.getFileHandle(String.format(DEFINITION_PATH, moduleReference.value))
                ?.let { fileLoader.loadAsString(it) } ?: return

        val blueprints = jsonParser.parseList(ObjectJson::class.java, definition)
                .asSequence()
                .map { it.toBlueprint() }.toList()

        blueprintRepository.save(blueprints)
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

    //region PERSISTENCE
    override fun produceState(): List<State> =
            instanceRepository.fetchAll().map { it.toPersistableState() }

    override fun ingestState(state: List<State>) {
        val instances = state.mapNotNull { instantiateFromState(it) }
        instanceRepository.save(instances)
    }

    private fun instantiateFromState(state: State): ObjectInstance? {
        val blueprint = blueprintRepository.find(Reference.to(state["blueprint"] as String? ?: return null)) ?: return null
        return Persistable.instantiate(state) {
            ObjectInstance.restore(
                    state["id"] as String? ?: return@instantiate null,
                    blueprint,
                    ExternalState.fromExistingState(state["externalState"] as Map<String, Any>)
            )
        }
    }
    //endregion
}