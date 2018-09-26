package com.github.rskupnik.thestory.domain.item.internal

import com.github.rskupnik.thestory.domain.item.ItemPlacement
import com.github.rskupnik.thestory.domain.shared.ExternalState
import com.github.rskupnik.thestory.domain.shared.persistence.PersistableState

internal data class ItemPersistableState(
        internal val id: String,
        internal val blueprint: String,
        internal val externalState: ExternalState,
        internal val currentImage: String?,
        internal val placement: ItemPlacement?
) : PersistableState {

    companion object {
        private fun fromRawData(map: Map<String, Any>): ItemPersistableState = ItemPersistableState(
                map["id"] as String,
                map["blueprint"] as String,
                ExternalState.fromExistingState(map["externalState"] as Map<String, Any>),
                map["currentImage"] as String,
                map["placement"] as ItemPlacement // TODO: Probably needs parsing
        )

        fun fromRawData(data: List<Map<String, Any>>): List<ItemPersistableState> = data.map { fromRawData(it) }
    }
}