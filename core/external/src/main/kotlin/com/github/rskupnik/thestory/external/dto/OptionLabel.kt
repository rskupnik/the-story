package com.github.rskupnik.thestory.external.dto

import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.entity.EntityId

// TODO: Move to feedback?
data class OptionLabel(
        val entityId: EntityId,
        val label: String,
        val context: Context?
)