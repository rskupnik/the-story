package com.github.rskupnik.thestory.domain.item

import com.github.rskupnik.thestory.core.callback.domain.Callback
import com.github.rskupnik.thestory.option.domain.Option
import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.Reference
import com.github.rskupnik.thestory.shared.persistence.PersistableProvider

interface ItemService : PersistableProvider {

    fun loadBlueprints(moduleReference: Reference)

    fun instantiate(blueprintReference: Reference): Reference?

    fun getItemView(reference: Reference): ItemView?

    fun getAllItemsView(): List<ItemView>

    fun getOptions(reference: Reference, context: Context?): List<Option>

    fun getCallbacks(reference: Reference): List<Callback>

    fun mutate(itemReference: Reference, mutator: ItemMutator): Boolean
}