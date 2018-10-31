package com.github.rskupnik.thestory.item.domain

import com.github.rskupnik.thestory.shared.Context

data class ItemPlacement(
        val context: Context,
        val slot: Any
) {
    companion object {
        fun fromRawData(data: Map<String, Any>?): ItemPlacement? {
            val context: Context = data?.get("context") as Context? ?: return null
            //val slot: ItemSlot = data?.get("slot") ?: return null
            return null
        }
    }
}