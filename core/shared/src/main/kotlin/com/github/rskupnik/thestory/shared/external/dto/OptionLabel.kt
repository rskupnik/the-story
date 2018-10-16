package com.github.rskupnik.thestory.shared.external.dto

import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.entity.EntityId

data class OptionLabel(
        val entityId: EntityId,
        val label: String,
        val context: Context?
)