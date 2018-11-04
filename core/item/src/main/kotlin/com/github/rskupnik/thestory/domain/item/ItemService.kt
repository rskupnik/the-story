package com.github.rskupnik.thestory.domain.item

import com.github.rskupnik.thestory.core.callback.domain.Callback
import com.github.rskupnik.thestory.item.domain.ItemView
import com.github.rskupnik.thestory.option.domain.Option
import com.github.rskupnik.thestory.persistence.Persister
import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.Reference

interface ItemService : Persister {

    fun loadBlueprints(moduleReference: Reference)

    fun instantiate(blueprintReference: Reference): Reference?

    fun getItemView(reference: Reference): ItemView?

    fun getAllItemsView(): List<ItemView>

    fun getOptions(reference: Reference, context: Context?): List<Option>

    fun getCallbacks(reference: Reference): List<Callback>

    fun mutate(itemReference: Reference, mutator: ItemMutator): Boolean
}