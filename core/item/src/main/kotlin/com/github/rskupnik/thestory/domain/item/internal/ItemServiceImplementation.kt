package com.github.rskupnik.thestory.domain.item.internal

import com.github.rskupnik.thestory.core.callback.domain.Callback
import com.github.rskupnik.thestory.domain.item.ItemMutator
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.domain.item.internal.ItemInstance.Companion.restore
import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.external.asset.Image
import com.github.rskupnik.thestory.external.file.FileLoader
import com.github.rskupnik.thestory.item.domain.ItemPlacement
import com.github.rskupnik.thestory.item.domain.ItemView
import com.github.rskupnik.thestory.option.domain.Option
import com.github.rskupnik.thestory.persistence.Persistable
import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.ExternalState
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.json.JsonParser
import com.github.rskupnik.thestory.shared.util.CommonFacadeOperations

typealias State = Map<String, Any?>

internal class ItemServiceImplementation(
        private val fileLoader: FileLoader,
        private val jsonParser: JsonParser,
        private val moduleService: ModuleService,
        private val blueprintRepository: ItemBlueprintRepository,
        private val instanceRepository: ItemInstanceRepository
) : ItemService {

    override val persistenceKey: String = "item"

    companion object {
        const val DEFINITION_PATH = "modules/unpacked/%s/definitions/items.json"
    }

    override fun loadBlueprints(moduleReference: Reference) {
        val definition = fileLoader.getFileHandle(String.format(DEFINITION_PATH, moduleReference.value))
                ?.let { fileLoader.loadAsString(it) } ?: return

        val blueprints = jsonParser.parseList(ItemJson::class.java, definition)
                .asSequence()
                .map { it.toBlueprint() }.toList()

        blueprintRepository.save(blueprints)
    }

    override fun instantiate(blueprintReference: Reference): Reference? {
        val blueprint = blueprintRepository.find(blueprintReference) ?: return null
        val instance = ItemInstance(blueprint)
        instance.externalState = ExternalState.fromExistingState(blueprint.initialState)
        return instanceRepository.save(instance)
    }

    override fun getItemView(reference: Reference): ItemView? {
        return buildItemView(instanceRepository.find(reference) ?: return null)
    }

    override fun getAllItemsView(): List<ItemView> =
            instanceRepository.fetchAll().map { buildItemView(it) }.filterNotNull()

    override fun getOptions(reference: Reference, context: Context?): List<Option> {
        val instance = instanceRepository.find(reference) ?: return emptyList()
        return instance.blueprint.options
                .let { if (context == null) it else Option.filterByContext(it, context) }
                .let { Option.filterByConditions(it, instance.externalState) }
    }

    override fun getCallbacks(reference: Reference): List<Callback> {
        val instance = instanceRepository.find(reference) ?: return emptyList()
        return instance.blueprint.callbacks.toList()
    }

    override fun mutate(itemReference: Reference, mutator: ItemMutator): Boolean {
        mutator.mutate(instanceRepository.find(itemReference) ?: return false)
        return true
    }

    //region PERSISTENCE
    override fun produceState(): List<State> =
        instanceRepository.fetchAll().map { it.toPersistableState() }

    override fun ingestState(state: List<State>) {
        val instances = state.mapNotNull { instantiateFromState(it) }
        instanceRepository.save(instances)
    }

    private fun instantiateFromState(state: State): ItemInstance? {
        val blueprint = blueprintRepository.find(Reference.to(state["blueprint"] as String? ?: return null)) ?: return null
        return Persistable.instantiate(state) {
            ItemInstance.restore(
                    state["id"] as String? ?: return@instantiate null,
                    blueprint,
                    if (state["currentImage"] != null) Reference.to(state["currentImage"] as String) else null,
                    ExternalState.fromExistingState(state["externalState"] as Map<String, Any>),
                    state["placement"] as ItemPlacement // TODO: Probably needs parsing
            )
        }
    }
    //endregion

    private fun buildItemView(item: ItemInstance): ItemView? {
        val image: Image = moduleService.getImage(item.currentImageReference ?: item.blueprint.imageReference) ?: return null
        return ItemView(
                item.reference,
                item.blueprint.reference,
                item.blueprint.label,
                image,
                ExternalState.fromExistingState(item.externalState),
                item.placement
        )
    }
}