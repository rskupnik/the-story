package com.github.rskupnik.thestory.domain.item.internal

import com.github.rskupnik.thestory.domain.callback.Callback
import com.github.rskupnik.thestory.domain.item.ItemMutator
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.domain.item.ItemView
import com.github.rskupnik.thestory.domain.module.ModuleService
import com.github.rskupnik.thestory.domain.option.Option
import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.ExternalState
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.external.asset.Image
import com.github.rskupnik.thestory.shared.external.file.FileLoader
import com.github.rskupnik.thestory.shared.json.JsonParser
import com.github.rskupnik.thestory.shared.util.CommonFacadeOperations

internal class ItemServiceImplementation(
        private val fileLoader: FileLoader,
        private val jsonParser: JsonParser,
        private val moduleService: ModuleService,
        private val blueprintRepository: ItemBlueprintRepository,
        private val instanceRepository: ItemInstanceRepository
) : ItemService {

    override val persistableKey: String = "item"

    companion object {
        const val DEFINITION_PATH = "modules/unpacked/%s/definitions/items.json"
    }

    override fun loadBlueprints(moduleReference: Reference) {
        blueprintRepository.save(
                CommonFacadeOperations.loadBlueprints(moduleReference, fileLoader, DEFINITION_PATH, jsonParser, ItemJson::class)
        )
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

    override fun getPersistableState(): List<ItemPersistableState> =
        CommonFacadeOperations.getPersistableState(instanceRepository.fetchAll())

    override fun loadPersistableState(state: List<Map<String, Any>>) {
        val loadedState = ItemPersistableState.fromRawData(state)
        val instances = loadedState.mapNotNull { instantiateFromState(it) }
        instanceRepository.save(instances)
    }

    private fun instantiateFromState(state: ItemPersistableState): ItemInstance? {
        val blueprint = blueprintRepository.find(Reference.to(state.blueprint)) ?: return null
        return ItemInstance.restore(state.id, blueprint,
                if (state.currentImage != null) Reference.to(state.currentImage) else null,
                state.externalState, state.placement
        )
    }

    private fun buildItemView(item: ItemInstance): ItemView? {
        val image: Image = moduleService.getImage(item.currentImageReference ?: item.blueprint.imageReference) ?: return null
        return ItemView.fromInstance(item, image)
    }
}