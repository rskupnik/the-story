package com.github.rskupnik.thestory.domain.`object`.internal

import com.github.rskupnik.thestory.shared.ExternalState
import com.github.rskupnik.thestory.shared.persistence.PersistableState

internal data class ObjectPersistableState(
        internal val id: String,
        internal val blueprint: String,
        internal val externalState: ExternalState
) : PersistableState {

    companion object {
        fun fromRawData(data: Map<String, Any>): ObjectPersistableState = ObjectPersistableState(
                data["id"] as String,
                data["blueprint"] as String,
                ExternalState.fromExistingState(data["externalState"] as Map<String, Any>)
        )

        fun fromRawData(data: List<Map<String, Any>>): List<ObjectPersistableState> = data.map { fromRawData(it) }
    }
}