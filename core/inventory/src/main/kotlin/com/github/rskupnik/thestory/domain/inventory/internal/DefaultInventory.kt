package com.github.rskupnik.thestory.domain.inventory.internal

import com.github.rskupnik.thestory.domain.inventory.Inventory
import com.github.rskupnik.thestory.domain.inventory.InventorySlot
import com.github.rskupnik.thestory.domain.item.ItemMutator
import com.github.rskupnik.thestory.domain.item.ItemPlacement
import com.github.rskupnik.thestory.domain.item.ItemService
import com.github.rskupnik.thestory.domain.item.ItemView
import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.Reference

internal class DefaultInventory(
        private val itemService: ItemService
        // TODO: OutputReceiver
) : Inventory {

    private val items = arrayOf<Array<Reference?>>(arrayOfNulls(4), arrayOfNulls(4), arrayOfNulls(4), arrayOfNulls(4), arrayOfNulls(4))

    override fun put(reference: Reference, slot: InventorySlot) {
        if (itemExists(reference) && slotFree(slot)) {
            // Put the item in the array
            items[slot.x][slot.y] = reference.clone()

            // Mutate item's placement param
            itemService.mutate(reference, ItemMutator.new().placement(ItemPlacement(Context.INVENTORY, slot)).build())
        }
    }

    override fun put(reference: Reference) {
        put(reference, getFirstFreeSlot() ?: return)
    }

    override fun get(slot: InventorySlot): Reference? = items[slot.x][slot.y]

    override fun getAll(): Map<InventorySlot, ItemView> =
            items.mapIndexed { i, arrayOfReferences ->
                arrayOfReferences.mapIndexedNotNull { j, reference ->
                    val itemView: ItemView? = reference?.let { itemService.getItemView(it) }
                    Pair(InventorySlot(i, j), itemView)
                }
            }
            .flatMap { it }
            .asSequence()
            .filter { it.second != null }
            .map { Pair(it.first, it.second!!) }
            .toList()
            .toMap()

    override fun remove(reference: Reference) {
        val slot = findItem(reference) ?: return
        items[slot.x][slot.y] = null
    }

    override fun findFreeSlot(): InventorySlot? = getFirstFreeSlot()

    override fun refresh() {
        // Get all available items
        val existingItems = itemService.getAllItemsView()

        // Purge the existing storage
        clear()

        // Put all existing items in the storage if their placement is in inventory
        existingItems.forEach {
            if (it.placement != null && it.placement?.context == Context.INVENTORY) {
                val slot = it.placement?.slot as InventorySlot
                items[slot.x][slot.y] = it.id.clone()
            }
        }

        // TODO: Inform the OutputReceiver
    }

    private fun clear() {
        items.forEachIndexed { i, v1 ->
            v1.forEachIndexed { j, _ ->
                items[i][j] = null
            }
        }
    }

    private fun findItem(item: Reference): InventorySlot? {
        items.forEachIndexed { i, v1 ->
            v1.forEachIndexed { j, v2 ->
                if (v2 != null && v2 == item)
                    return InventorySlot(i, j)
            }
        }
        return null
    }

    private fun getFirstFreeSlot(): InventorySlot? {
        items.forEachIndexed { i, v1 ->
            v1.forEachIndexed { j, v2 ->
                if (v2 != null)
                    return InventorySlot(i, j)
            }
        }
        return null
    }

    private fun itemExists(reference: Reference) = itemService.getItemView(reference) != null

    private fun slotFree(slot: InventorySlot) = slotFree(slot.x, slot.y)

    private fun slotFree(x: Int, y: Int) = items[x][y] == null
}