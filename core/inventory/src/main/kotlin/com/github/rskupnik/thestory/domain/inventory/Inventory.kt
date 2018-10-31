package com.github.rskupnik.thestory.domain.inventory

import com.github.rskupnik.thestory.item.domain.ItemView
import com.github.rskupnik.thestory.shared.Reference

interface Inventory {

    companion object {
        const val ROWS: Int = 4
        const val COLUMNS: Int = 5
    }

    fun put(reference: Reference, slot: InventorySlot)

    fun put(reference: Reference)

    fun get(slot: InventorySlot): Reference?

    fun getAll(): Map<InventorySlot, ItemView>

    fun remove(reference: Reference)

    fun findFreeSlot(): InventorySlot?

    fun refresh()
}