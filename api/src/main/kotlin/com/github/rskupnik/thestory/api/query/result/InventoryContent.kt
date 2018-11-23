package com.github.rskupnik.thestory.api.query.result

import com.github.rskupnik.thestory.item.domain.InventorySlot
import com.github.rskupnik.thestory.item.domain.ItemView

data class InventoryContent(val inventory: Map<InventorySlot, ItemView>)