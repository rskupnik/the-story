package com.github.rskupnik.thestory.item.domain

import com.github.rskupnik.thestory.external.asset.Image
import com.github.rskupnik.thestory.shared.ExternalState
import com.github.rskupnik.thestory.shared.Reference

data class ItemView(
        val id: Reference,
        val blueprintId: Reference,
        val label: String,
        val image: Image,
        val externalState: ExternalState,
        val placement: ItemPlacement?
)