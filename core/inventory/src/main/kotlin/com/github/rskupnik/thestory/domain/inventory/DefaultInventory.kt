package com.github.rskupnik.thestory.domain.inventory

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

    }

    override fun get(slot: InventorySlot): Reference? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(): Map<InventorySlot, ItemView> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(reference: Reference) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findFreeSlot(): InventorySlot? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refresh() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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