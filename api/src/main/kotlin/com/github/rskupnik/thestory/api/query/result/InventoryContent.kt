package com.github.rskupnik.thestory.control.query.result

import com.github.rskupnik.thestory.domain.inventory.InventorySlot
import com.github.rskupnik.thestory.item.domain.ItemView

data class InventoryContent(val inventory: Map<InventorySlot, ItemView>)