package com.github.rskupnik.thestory.domain.item.internal

import com.github.rskupnik.thestory.domain.shared.persistence.PersistableState

internal data class ItemPersistableState(
        private val id: String,
        private val blueprint: String,
        //private val externalState: ExternalState,
        private val currentImage: String
        //private val placement: ItemPlacement
) : PersistableState {

    companion object {
        fun fromRawData(map: Map<String, Any>): ItemPersistableState = ItemPersistableState(
                map["id"] as String,
                map["blueprint"] as String,
                // ExternalState
                map["currentImage"] as String
                // ItemPlacement
        )

        fun fromRawData(data: List<Map<String, Any>>): List<ItemPersistableState> = data.map { fromRawData(it) }
    }
}