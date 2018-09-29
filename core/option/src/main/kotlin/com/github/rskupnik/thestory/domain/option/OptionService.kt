package com.github.rskupnik.thestory.domain.option

import com.github.rskupnik.thestory.shared.Context
import com.github.rskupnik.thestory.shared.entity.EntityId

interface OptionService {
    fun execute(option: Option, externalData: Map<String, Any>, context: Context, entityId: EntityId)
}