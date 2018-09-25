package com.github.rskupnik.thestory.domain.item.internal

import com.github.rskupnik.thestory.domain.item.ItemMutator
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.domain.item.ItemView
import com.github.rskupnik.thestory.domain.shared.ExternalState
import com.github.rskupnik.thestory.domain.shared.Reference
import com.github.rskupnik.thestory.domain.shared.external.FileLoader
import com.github.rskupnik.thestory.domain.shared.external.JsonParser

internal class ItemServiceImplementation(
        private val fileLoader: FileLoader,
        private val jsonParser: JsonParser,
        private val moduleFacade: ModuleFacade,
        private val blueprintRepository: ItemBlueprintRepository,
        private val instanceRepository: ItemInstanceRepository
        //private val commonFacadeOperations: CommonFacadeOperations
) : ItemService {

    companion object {
        const val DEFINITION_PATH = "modules/unpacked/%s/definitions/items.json"
    }

    override fun loadBlueprints(moduleReference: Reference) {
        val blueprints: List<ItemBlueprint> = commonFacadeOperations.loadBlueprints(
                moduleReference, fileLoader, DEFINITION_PATH, jsonParser, ItemJsonRepresentation::class.java)

        blueprintRepository.save(blueprints)
    }

    override fun instantiate(blueprintReference: Reference): Reference? {
        val blueprint = blueprintRepository.find(blueprintReference) ?: return null
        val instance = ItemInstance(blueprint)
        instance.externalState = ExternalState.fromExistingState(blueprint.initialState)
        return instanceRepository.save(instance)externalState
    }

    override fun getItemView(reference: Reference): ItemView? {
        val item = instanceRepository.find(reference) ?: return null
        return buildItemView(item)
    }

    override fun getAllItemsView(): List<ItemView> {
        return instanceRepository.fetchAll().map { buildItemView(it) }.filterNotNull()
    }

    /*override fun getOptions(reference: Reference, context: Context?): List<Option> {
        val instance = instanceRepository.find(reference) ?: return emptyList()
        val filteredByContext = if (context == null) instance.blueprint.options else Option.Filter.byContext(instance.blueprint.options, context)
        val filteredByConditions = Option.Filter.byConditions(filteredByContext, instance.externalState)
        return filteredByConditions
    }

    override fun getCallbacks(reference: Reference): List<Callback> {
        val instance = instanceRepository.find(reference) ?: return emptyList()
        return instance.blueprint.callbacks.toList()
    }*/

    override fun mutate(itemReference: Reference, mutator: ItemMutator): Boolean {
        mutator.mutate(instanceRepository.find(itemReference))
        return true
    }

    override fun getPersistableKey(): String = "items"

    override fun getPersistableState(): MutableList<ItemPersistableState> {
        return commonFacadeOperations.getPersistableState(instanceRepository.fetchAll())
    }

    override fun loadPersistableState(state: MutableList<MutableMap<String, Any>>?) {
        val loadedState = ItemPersistableState.fromRawData(state)
        val instances = loadedState.map { instantiateFromState(it) }.filterNotNull()
        instanceRepository.save(instances)
    }

    private fun instantiateFromState(state: ItemPersistableState): ItemInstance? {
        val blueprint = blueprintRepository.find(Reference(state.blueprint)) ?: return null
        return ItemInstance.restore(state.id, blueprint, state.externalState,
                if (state.currentImage != null) Reference(state.currentImage) else null,
                state.placement
        )
    }

    private fun buildItemView(item: ItemInstance): ItemView? {
        val imageReference = item.currentImage ?: item.blueprint.image
        val imageOpt: Optional<Image> = moduleFacade.getImage(imageReference)
        val image: Image = if (imageOpt.isPresent) imageOpt.get() else return null
        return ItemView.fromInstance(item, image)
    }
}