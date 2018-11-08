package com.github.rskupnik.thestory.item.domain

import com.github.rskupnik.thestory.shared.Context

data class ItemPlacement(
        val context: Context,
        val slot: Any
) {
    companion object {
        fun fromRawData(data: Map<String, Any>?): ItemPlacement? {
            val context: Context = data?.get("context") as Context? ?: return null
            val slot = restoreSlot(context, requireNotNull(data?.get("slot")))
            return ItemPlacement(context, slot)
        }

        private fun restoreSlot(context: Context, raw: Any) =
                if (context == Context.EQUIPMENT) EquipmentSlot.valueOf(raw as String)
                else InventorySlot((raw as Map<String, Int>)["x"] as Int, (raw as Map<String, Int>)["y"] as Int)
    }
}