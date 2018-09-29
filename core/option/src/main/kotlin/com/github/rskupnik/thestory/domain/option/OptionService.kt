package com.github.rskupnik.thestory.domain.option

import com.github.rskupnik.thestory.shared.Context

interface OptionService {
    fun execute(option: Option, externalData: Map<String, Any>, context: Context, entityId: EntityId)
}